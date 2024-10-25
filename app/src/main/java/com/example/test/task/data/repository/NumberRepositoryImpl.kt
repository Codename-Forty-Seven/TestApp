package com.example.test.task.data.repository

import com.example.test.task.data.local.FactDao
import com.example.test.task.presentation.model.FactUi
import com.example.test.task.data.model.FactResponse
import com.example.test.task.data.remote.NumbersApiService
import com.example.test.task.data.remote.RetrofitInstance
import com.example.test.task.domain.repository.NumbersRepository

class NumberRepositoryImpl(
    private val dao: FactDao,
    private val numbersApiService: NumbersApiService
) : NumbersRepository {

    override suspend fun getFactForNumber(number: Int): FactResponse {
        return numbersApiService.getFactForNumber(number)
    }

    override suspend fun getRandomMathFact(): FactResponse {
        return RetrofitInstance.retrofit.getRandomFact()
    }

    override suspend fun insertFact(factUi: FactUi) {
        dao.insert(factUi)
    }

    override suspend fun getAllFacts(): List<FactUi> {
        return dao.getAllFacts()
    }
}