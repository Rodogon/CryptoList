package cl.desafiolatam.cryptolist.domain

import cl.desafiolatam.cryptolist.data.CryptoRepository
import cl.desafiolatam.cryptolist.data.model.Crypto

class GetCryptomonedasUseCase {
    private val repository = CryptoRepository()

    suspend operator fun invoke() = repository.getAllCrypto()
}