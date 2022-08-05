package cl.desafiolatam.cryptolist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import cl.desafiolatam.cryptolist.data.database.CryptoEntity
import cl.desafiolatam.cryptolist.data.repository.CryptoRepository
import cl.desafiolatam.cryptolist.utils.UpdateData
import kotlinx.coroutines.runBlocking

class CryptoViewModel(private val repository: CryptoRepository): ViewModel() {
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allCrypto: LiveData<List<CryptoEntity>> by lazy {
        repository.allCrypto.also {
            getUpdatdData()
        }
    }

    private fun getUpdatdData() {
        runBlocking {
            UpdateData.getUpdatedData()
        }
    }

    fun updateData() {
        runBlocking {
            UpdateData.updatedData()
        }
    }
}

class CryptoViewModelFactory(private val repository: CryptoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CryptoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}