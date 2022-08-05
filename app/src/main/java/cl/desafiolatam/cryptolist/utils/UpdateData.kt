package cl.desafiolatam.cryptolist.utils

import android.util.Log
import cl.desafiolatam.cryptolist.CryptoListApp
import cl.desafiolatam.cryptolist.core.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UpdateData {
    companion object {

        // ESTE METODO SIRVE PARA OBTENER LOS DATOS ACTUALIZADOS CUANDO SE INSTALA LA APP

        suspend fun getUpdatedData() = coroutineScope {
            launch(Dispatchers.IO) {
                val service = RetrofitClient.retrofitInstance()
                val response = service.getAllCrypto()
                val data = response.body()

                val app = CryptoListApp()
                app.repository.deleteAll()

                if (response.isSuccessful) {
                    if (data != null) {

                        for (asset in data.data) {
                            Log.i("Asset ", asset.id)
                            app.repository.insert(asset)
                        }
                    }
                } else {
                    Log.e("UpdateData Error", "Ocurrió un error al ejecutar getUpdatedData")
                }
            }
        }
    }
}