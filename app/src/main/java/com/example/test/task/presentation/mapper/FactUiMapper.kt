package com.example.test.task.presentation.mapper

import com.example.test.task.data.model.FactResponse
import com.example.test.task.domain.mapper.base.Mapper
import com.example.test.task.presentation.model.FactUi

class FactUiMapper : Mapper<FactResponse, FactUi>() {
    override fun map(from: FactResponse) = from.run {
        FactUi(
            number = this.number,
            fact = this.text
        )
    }
}