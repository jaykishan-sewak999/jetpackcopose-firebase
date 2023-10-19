package com.jecky.jetpackcoposefirebase.ui.quote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jecky.jetpackcoposefirebase.repository.QuotesRepository
import com.jecky.jetpackcoposefirebase.repository.model.Quote
import kotlinx.coroutines.launch

class QuoteViewModel(
    private val quotesRepository: QuotesRepository
) : ViewModel() {

    var loading: Boolean by mutableStateOf(false)

    private var _isQuoteAdded: MutableLiveData<Boolean> = MutableLiveData()
    var isQuoteAdded: LiveData<Boolean> = _isQuoteAdded

    fun addQuote(quote: Quote) {
        try {
            viewModelScope.launch {
                var response = quotesRepository.addQuote(quote)
                _isQuoteAdded.postValue(true)
            }
        } catch (exception: Exception) {
            _isQuoteAdded.postValue(false)
        }
    }
}


class QuoteViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
            return QuoteViewModel(QuotesRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}