package cl.desafiolatam.cryptolist.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import cl.desafiolatam.cryptolist.core.RetrofitClient
import cl.desafiolatam.cryptolist.data.database.CryptoDao
import cl.desafiolatam.cryptolist.data.database.CryptoDatabase
import cl.desafiolatam.cryptolist.data.database.CryptoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoRepository(private val cryptoDao: CryptoDao) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allCrypto: LiveData<List<CryptoEntity>> = cryptoDao.getAllCrypto()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(crypto: CryptoEntity) {
        cryptoDao.insert(crypto)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(crypto: CryptoEntity) {
        cryptoDao.update(crypto)
    }
}