package com.example.programovilexa3.swapi.data.repository

import com.example.programovilexa3.swapi.data.datasource.SwapiRemoteDataSource
import com.example.programovilexa3.swapi.data.mapper.toDomain
import com.example.programovilexa3.swapi.domain.model.SwapiPeoplePage
import com.example.programovilexa3.swapi.domain.repository.SwapiPeopleRepository
import kotlinx.coroutines.CancellationException

class SwapiPeopleRepositoryImpl(private val source: SwapiRemoteDataSource) : SwapiPeopleRepository {
    override suspend fun getPeoplePage(page: Int): Result<SwapiPeoplePage> = try {
        Result.success(source.getPeoplePage(page).toDomain())
    } catch (cancel: CancellationException) {
        throw cancel
    } catch (error: Exception) {
        Result.failure(error)
    }
}
