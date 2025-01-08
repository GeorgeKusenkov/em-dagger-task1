package com.example.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.home.domain.GetRandomCatUrlUseCase
import com.example.feature.home.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getCatUseCase: GetRandomCatUrlUseCase,
    private val getQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {

    private val _catState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val catState: StateFlow<UiState<String>> = _catState.asStateFlow()

    private val _quoteState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val quoteState: StateFlow<UiState<String>> = _quoteState.asStateFlow()

    init {
        fetchCats()
        fetchBreakingBadQuotes()
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
                    _catState.value = UiState.Success(catImage.url)
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
                    _quoteState.value = UiState.Success(quote.text)
                },
                onFailure = { error ->
                    _quoteState.value = UiState.Error("Failed to load quiote: ${error.localizedMessage}")
                }
            )
        }
    }
}