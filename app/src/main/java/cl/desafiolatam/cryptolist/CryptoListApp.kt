package cl.desafiolatam.cryptolist

import android.app.Application
import cl.desafiolatam.cryptolist.data.database.CryptoDatabase
import cl.desafiolatam.cryptolist.data.repository.CryptoRepository

class CryptoListApp: Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { CryptoDatabase.getDatabase(this) }
    val repository by lazy { CryptoRepository(database.cryptoDao()) }
}