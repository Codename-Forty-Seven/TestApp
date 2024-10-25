package com.example.test.task.data.model

data class FactResponse(
    val text: String,
    val found: Boolean,
    val number: Int,
    val type: String,
    val date: String? = null,
    val year: String? = null
)

