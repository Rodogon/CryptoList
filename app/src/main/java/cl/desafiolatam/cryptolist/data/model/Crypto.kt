package cl.desafiolatam.cryptolist.data.model

import cl.desafiolatam.cryptolist.data.database.CryptoEntity
import com.google.gson.annotations.SerializedName

data class Crypto(
	val data: ArrayList<CryptoEntity>,
	val timestamp: Long
)
