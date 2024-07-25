package com.fredprojects.helloworld.presentation.features.clients.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.helloworld.domain.core.repositories.IClientRepository
import com.fredprojects.helloworld.domain.core.utils.ConnectionStatus
import com.fredprojects.helloworld.domain.features.clients.common.AnimeQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * AnimeQuotesVM is used to get data from the server
 * @param repository the repository used to get data from the server
 */
class AnimeQuotesVM(
    private val repository: IClientRepository<AnimeQuote>
) : ViewModel() {
    /**
     * @see animeQuotesMSF is used to emit data to the state flow
     * @see animeQuotes is used to display data in the view
     */
    private val animeQuotesMSF = MutableStateFlow<ConnectionStatus<AnimeQuote>>(ConnectionStatus.Loading(emptyList()))
    val animeQuotes = animeQuotesMSF.asStateFlow()
    /**
     * getAnimeQuotes is used to get data from the server and emit it to the state flow
     * @param data the anime name to be sent to the server
     */
    fun getAnimeQuotes(data: String) {
        viewModelScope.launch {
            repository.getData(data).flowOn(Dispatchers.IO).collectLatest {
                animeQuotesMSF.emit(it)
            }
        }
    }
}