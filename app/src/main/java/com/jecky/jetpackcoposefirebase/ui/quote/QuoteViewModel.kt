package com.jecky.jetpackcoposefirebase.ui.quote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jecky.jetpackcoposefirebase.network.APIResult
import com.jecky.jetpackcoposefirebase.repository.QuotesRepository
import com.jecky.jetpackcoposefirebase.repository.UserRepository
import com.jecky.jetpackcoposefirebase.repository.model.Quote
import kotlinx.coroutines.launch

class QuoteViewModel(
    private val quotesRepository: QuotesRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    var loading: Boolean by mutableStateOf(false)
    private var _isQuoteAdded: MutableLiveData<Boolean> = MutableLiveData()

    private var _quotesList: MutableLiveData<List<Quote>> = MutableLiveData()
    var quoteList: LiveData<List<Quote>> = _quotesList

    private var _favQuotesList: MutableLiveData<List<Quote>> = MutableLiveData()
    var favQuotesList: LiveData<List<Quote>> = _favQuotesList

    fun addQuote(quote: Quote) {
        try {
            loading = true
            viewModelScope.launch {
                quotesRepository.addQuote(quote)
                loading = false
                _isQuoteAdded.postValue(true)
            }
        } catch (exception: Exception) {
            loading = false
            _isQuoteAdded.postValue(false)
        }
    }

    fun getQuotes(category: String?) {
        try {
            viewModelScope.launch {
                loading = true
                val quoteListResponse = quotesRepository.getQuotes(category)
                if (quoteListResponse is APIResult.APISuccess) {
                    _quotesList.postValue(quoteListResponse.data)
                } else {
                    //todo need to manage error case
                }
                loading = false
            }
        } catch (exception: Exception) {
            loading = false
        }
    }

    fun getMyQuotes() {
        try {
            viewModelScope.launch {
                loading = true
                val quoteListResponse = quotesRepository.getMyQuotes()
                _quotesList.postValue(quoteListResponse.data)
                loading = false
            }
        } catch (exception: Exception) {
            loading = false
        }
    }


    fun addQuoteToFavorite(quoteId: String) {
        try {
            viewModelScope.launch {
                userRepository.addFavoriteQuote(quoteId)
            }
        } catch (exception: Exception) {

        }
    }

    fun removeQuoteToFavorite(quoteId: String) {
        try {
            viewModelScope.launch {
                userRepository.removeFavoriteQuote(quoteId)
            }
        } catch (_: Exception) {

        }
    }

    fun getMyFavoriteQuotes() {
        try {
            viewModelScope.launch {
                loading = true
                val quoteListResponse = quotesRepository.getMyFavoriteQuotes()
                _quotesList.postValue(quoteListResponse.data)
                loading = false
            }
        } catch (exception: Exception) {
            loading = false
        }
    }

}

class QuoteViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
            return QuoteViewModel(QuotesRepository(), UserRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}