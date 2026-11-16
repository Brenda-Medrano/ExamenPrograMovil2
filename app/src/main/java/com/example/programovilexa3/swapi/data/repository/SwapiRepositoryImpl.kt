package com.example.programovilexa3.swapi.data.repository

import com.example.programovilexa3.swapi.data.datasource.SwapiRemoteDataSource
import com.example.programovilexa3.swapi.data.mapper.toDomain
import com.example.programovilexa3.swapi.domain.model.SwapiCharacter
import com.example.programovilexa3.swapi.domain.repository.SwapiRepository
import kotlinx.coroutines.CancellationException

class SwapiRepositoryImpl(private val source: SwapiRemoteDataSource) : SwapiRepository {
    override suspend fun getCharacter(id: Int): Result<SwapiCharacter> = try {
        Result.success(source.getCharacter(id).toDomain())
    } catch (cancel: CancellationException) {
        throw cancel
    } catch (error: Exception) {
        Result.failure(error)
    }
}
