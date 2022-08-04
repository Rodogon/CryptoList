package cl.desafiolatam.cryptolist.data.model

import com.google.gson.annotations.SerializedName

data class Crypto(

	@field:SerializedName("data")
	val data: ArrayList<Data>,

	@field:SerializedName("timestamp")
	val timestamp: Long
)
