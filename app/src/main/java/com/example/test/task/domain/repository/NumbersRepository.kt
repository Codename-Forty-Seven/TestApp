package com.example.test.task.domain.repository

import com.example.test.task.presentation.model.FactUi
import com.example.test.task.data.model.FactResponse

interface NumbersRepository {
    suspend fun getFactForNumber(number: Int): FactResponse
    suspend fun getRandomMathFact(): FactResponse
    suspend fun insertFact(factUi: FactUi)
    suspend fun getAllFacts(): List<FactUi>
}