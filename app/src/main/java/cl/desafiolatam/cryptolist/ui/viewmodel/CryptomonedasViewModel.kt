package cl.desafiolatam.cryptolist.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import cl.desafiolatam.cryptolist.data.model.Crypto
import cl.desafiolatam.cryptolist.domain.GetCryptomonedasUseCase
import kotlinx.coroutines.launch

class CryptomonedasViewModel : ViewModel() {

    val CryptoList = MutableLiveData<List<Crypto>>()
    val isLoading = MutableLiveData<Boolean>()
    var getCryptomonedasUseCase = GetCryptomonedasUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getCryptomonedasUseCase()
            if (!result.isNullOrEmpty())
                CryptoList.postValue(result)
            isLoading.postValue(false)
        }
    }


}