package com.example.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.home.domain.GetRandomCatUrlUseCase
import com.example.feature.home.domain.GetRandomQuoteUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class HomeViewModel @AssistedInject constructor(
    private val getCatUseCase: GetRandomCatUrlUseCase,
    private val getQuoteUseCase: GetRandomQuoteUseCase,
    @Assisted private val time: Long
) : ViewModel() {

    private val _catState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val catState: StateFlow<UiState<String>> = _catState.asStateFlow()

    private val _quoteState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val quoteState: StateFlow<UiState<String>> = _quoteState.asStateFlow()

    private var updateJob: Job? = null


    @AssistedFactory
    interface Factory {
        fun create(updateIntervalSeconds: Long): HomeViewModel
    }

    init {
        fetchCats()
        fetchBreakingBadQuotes()
        startAutoUpdate()
    }

    private fun startAutoUpdate() {
        updateJob?.cancel() // Отменяем предыдущую корутину, если она существует

        updateJob = viewModelScope.launch {
            while (isActive) {
                delay(time * 1000) // Преобразуем секунды в миллисекунды
                fetchCats()
                fetchBreakingBadQuotes()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        updateJob?.cancel()
    }

    fun update() {
        fetchCats()
        fetchBreakingBadQuotes()
    }

    private fun fetchCats() {
        viewModelScope.launch {
            _catState.value = UiState.Loading

            getCatUseCase().fold(
                onSuccess = { catImage ->
                    _catState.value = UiState.Success(catImage)
                },
                onFailure = { error ->
                    _catState.value = UiState.Error("Failed to load cat: ${error.localizedMessage}")
                }
            )
        }
    }

    private fun fetchBreakingBadQuotes() {
        viewModelScope.launch {
            _quoteState.value = UiState.Loading

            getQuoteUseCase().fold(
                onSuccess = { quote ->
                    _quoteState.value = UiState.Success(quote)
                },
                onFailure = { error ->
                    _quoteState.value = UiState.Error("Failed to load quiote: ${error.localizedMessage}")
                }
            )
        }
    }
}