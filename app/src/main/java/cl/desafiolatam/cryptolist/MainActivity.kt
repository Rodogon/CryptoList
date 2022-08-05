package cl.desafiolatam.cryptolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import cl.desafiolatam.cryptolist.core.RetrofitClient
import cl.desafiolatam.cryptolist.data.model.Crypto
import cl.desafiolatam.cryptolist.databinding.ActivityMainBinding
import cl.desafiolatam.cryptolist.ui.view.CryptoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.ArrayList

/*
[X] Creación del proyecto e inicialización de control de versiones
[X] Consumo de API
   [X] dependencias
   [X] POJOs
   [X] Interfaz de operaciones
   [X] cliente de retrofit
   [X] permiso de internet
[X] Repositorio
[ ] ROOM
   [ ] dependencias
   [ ] Entities
   [ ] Dao
   [ ] cliente de base de datos
   [ ] subclase de application -> agregar al manifest
   [ ] Converters
   [ ] Test unitario a conversor de modelo a DB
[X] ViewBinding
   [X] Habilitar
   [ ] actualizar fragmento de listado
   [ ] actualizar fragmento de detalle
[X] corutinas (dependencias)
[ ] ViewModel (by viewModels()) -> Implementa el patrón factory [ ] Actualizar MainActivity y su layout
[ ] Fragmento de listado
   [ ] Crear Fragmento
   [ ] Layout de item list
   [ ] Adapter + ViewHolder + ReciclerView
   [ ] dependencia imágenes
   [ ] onClickListener al elemento del listado
   [ ] Abrir fragmento de detalle
[ ] Fragmento de detalle
   [ ] Crear Fragmento y layout
   [ ] Utilizar ViewModel para pedir información de detalle
[ ] Nombre de usuario utilizando SharedPreferences
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //private val cryptomonedasViewModel: CryptomonedasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadApiData()
    }

    private fun initRecyclerView(cryptomonedas: ArrayList<Data>) {
        val recyclerView = binding.rvcrypto
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CryptoAdapter(cryptomonedas)
    }

    private fun loadApiData() {
        CoroutineScope(Dispatchers.IO).launch {
            val service = RetrofitClient.retrofitInstance()
            val call: Response<Crypto> = service.getAllCrypto()
            val crypto = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    if (crypto != null) {
                        initRecyclerView(crypto.data)
                    }
                } else {
                    showToast()
                }
            }
        }
    }

    private fun showToast() {
        Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show()
    }


}