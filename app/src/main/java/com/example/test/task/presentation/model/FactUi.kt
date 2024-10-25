package com.example.test.task.presentation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts_table")
data class FactUi(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val number: Int,
    val fact: String
){
    constructor(
        number: Int,
        fact: String
    ):this(
        id = null,
        number = number,
        fact = fact
    )
}
