package cl.desafiolatam.cryptolist.data.network

import cl.desafiolatam.cryptolist.core.RetrofitClient
import cl.desafiolatam.cryptolist.data.model.Crypto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoService {
    private val retrofit = RetrofitClient.retrofitInstance()

    suspend fun getAllCrypto(): List<Crypto> {
       return withContext(Dispatchers.IO){
           val response = retrofit.create(Api::class.java).getAllCrypto()
           response.body() ?: emptyList()
       }
    }

    /*suspend fun getCrypto(id: String): Crypto{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(Api::class.java).getCrypto(id)
            (response.body() ?: emptySet<Crypto>()) as Crypto
        }
    }
     */
}