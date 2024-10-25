package com.example.test.task.presentation.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.task.presentation.model.FactUi
import com.example.test.task.data.model.FactResponse
import com.example.test.task.data.repository.NumberRepositoryImpl
import com.example.test.task.domain.mapper.base.Mapper
import com.example.test.task.presentation.mapper.FactUiMapper
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: NumberRepositoryImpl,
    private val mapper: Mapper<FactResponse, FactUi>
) : ViewModel() {
    private val TAG = "MainViewModel"
    private val _facts = MutableLiveData<List<FactUi>>()
    val facts: LiveData<List<FactUi>> get() = _facts

    private val _fact = MutableLiveData<FactUi>()
    val fact: LiveData<FactUi> get() = _fact

    fun getFactForNumber(number: Int) {
        Log.d(TAG, "getFactForNumber: $number")
        viewModelScope.launch {
            _fact.value = mapper.map(repository.getFactForNumber(number))
        }
    }

    fun getRandomMathFact() {
        Log.d(TAG, "getRandomMathFact")
        viewModelScope.launch {
            _fact.value = mapper.map(repository.getRandomMathFact())
        }
    }

    fun getAllFacts() {
        Log.d(TAG, "getAllFacts")
        viewModelScope.launch {
            _facts.value = repository.getAllFacts()
        }
    }

    fun insertFact(newFactUi: FactUi) {
        Log.d(TAG, "insertFact")
        viewModelScope.launch {
            repository.insertFact(newFactUi)
            getAllFacts()
        }
    }
}