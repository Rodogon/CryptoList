package cl.desafiolatam.cryptolist.data.network

import cl.desafiolatam.cryptolist.data.model.Crypto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("assets")
    suspend fun getAllCrypto(): Response<Crypto>

    //@GET("/assets/{id}")
    //suspend fun getCrypto(@Path("id") id:String): Response<Crypto>
}