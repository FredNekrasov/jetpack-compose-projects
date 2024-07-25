package com.fredprojects.helloworld.presentation.features.clients.anime.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.helloworld.domain.core.repositories.IClientRepository
import com.fredprojects.helloworld.domain.core.utils.ConnectionStatus
import com.fredprojects.helloworld.domain.features.clients.common.AnimeQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AnimeQuotesVM(
    private val repository: IClientRepository<AnimeQuote>
) : ViewModel() {
    private val animeQuotesMSF = MutableStateFlow<ConnectionStatus<AnimeQuote>>(ConnectionStatus.Loading(emptyList()))
    val animeQuotes = animeQuotesMSF.asStateFlow()
    fun getAnimeQuotes(data: String) {
        viewModelScope.launch {
            repository.getData(data).flowOn(Dispatchers.IO).collectLatest {
                animeQuotesMSF.emit(it)
            }
        }
    }
}