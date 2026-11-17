package com.example.programovilexa3.swapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programovilexa3.swapi.domain.usecase.GetSwapiCharacterUseCase
import com.example.programovilexa3.swapi.domain.usecase.GetSwapiPeopleUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SwapiViewModel(
    private val getCharacter: GetSwapiCharacterUseCase,
    private val getPeople: GetSwapiPeopleUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SwapiUiState())
    val state: StateFlow<SwapiUiState> = _state
    private var nextPage = 1
    private var listJob: Job? = null
    private var detailJob: Job? = null

    private fun updateState(transform: (SwapiUiState) -> SwapiUiState) {
        _state.value = transform(_state.value)
    }

    init { loadPeople() }

    fun loadPeople() {
        if (listJob?.isActive == true || !_state.value.hasNext) return
        val firstPage = _state.value.characters.isEmpty()
        updateState { it.copy(loadingList = firstPage, loadingMore = !firstPage, listError = null) }
        listJob = viewModelScope.launch {
            getPeople(nextPage).fold(
                onSuccess = { page ->
                    updateState { current ->
                        current.copy(
                            characters = (current.characters + page.characters).distinctBy { it.id },
                            loadingList = false,
                            loadingMore = false,
                            hasNext = page.hasNext
                        )
                    }
                    nextPage++
                },
                onFailure = {
                    updateState { current ->
                        current.copy(
                            loadingList = false,
                            loadingMore = false,
                            listError = "No se pudo cargar el catálogo. Revisa Internet y reintenta."
                        )
                    }
                }
            )
        }
    }

    fun selectCharacter(id: Int) {
        if (id <= 0) return
        detailJob?.cancel()
        updateState {
            it.copy(selectedId = id, selectedCharacter = null, loadingDetails = true, detailError = null)
        }
        detailJob = viewModelScope.launch {
            getCharacter(id).fold(
                onSuccess = { character ->
                    updateState { current ->
                        current.copy(selectedCharacter = character, loadingDetails = false)
                    }
                },
                onFailure = {
                    updateState { current ->
                        current.copy(
                            loadingDetails = false,
                            detailError = "No se pudieron cargar los datos. Vuelve a intentarlo."
                        )
                    }
                }
            )
        }
    }

    fun retryDetails() {
        _state.value.selectedId?.let(::selectCharacter)
    }

    fun backToList() {
        detailJob?.cancel()
        updateState {
            it.copy(
                selectedId = null,
                selectedCharacter = null,
                loadingDetails = false,
                detailError = null
            )
        }
    }
}
