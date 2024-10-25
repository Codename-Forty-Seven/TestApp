package com.example.test.task.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.task.data.model.FactResponse
import com.example.test.task.data.repository.NumberRepositoryImpl
import com.example.test.task.domain.mapper.base.Mapper
import com.example.test.task.presentation.model.FactUi

class MainViewModelFactory(
    private val repository: NumberRepositoryImpl,
    private val mapper: Mapper<FactResponse, FactUi>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST") // Щоб уникнути помилки при кастуванні
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository, mapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}