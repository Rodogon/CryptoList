package cl.desafiolatam.cryptolist.model.remote

import cl.desafiolatam.cryptolist.model.Crypto
import cl.desafiolatam.cryptolist.model.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/assets")
    suspend fun getAllCrypto(): Response<ArrayList<Crypto>>

    @GET("/assets/{id}")
    suspend fun getCrypto(@Path("id") id:String): Response<Crypto>
}