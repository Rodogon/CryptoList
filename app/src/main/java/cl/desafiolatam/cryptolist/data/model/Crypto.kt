package cl.desafiolatam.cryptolist.data.model

import cl.desafiolatam.cryptolist.data.database.CryptoEntity


data class Crypto(
	val data: ArrayList<CryptoEntity>,
	val timestamp: Long
)
