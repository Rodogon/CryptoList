package cl.desafiolatam.cryptolist.data

import cl.desafiolatam.cryptolist.data.model.Crypto
import cl.desafiolatam.cryptolist.data.model.CryptoProvider
import cl.desafiolatam.cryptolist.data.network.CryptoService

class CryptoRepository {
    private val api = CryptoService()

    suspend fun getAllCrypto(): List<Crypto> {
        val response = api.getAllCrypto()
        CryptoProvider.cryptoMonedas = response
        return response
    }

   /* suspend fun getCrypto(id: String): Crypto {
        val response = api.getCrypto(id)
        CryptoProvider.cryptomoneda = response
        return response
    }
    */
}