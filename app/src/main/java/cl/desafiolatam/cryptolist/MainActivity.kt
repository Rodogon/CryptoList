package cl.desafiolatam.cryptolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.desafiolatam.cryptolist.databinding.ActivityMainBinding
import cl.desafiolatam.cryptolist.ui.viewmodel.CryptomonedasViewModel

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
[X] ViewModel (by viewModels()) -> Implementa el patrón factory [ ] Actualizar MainActivity y su layout
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

        val cryptomonedasViewModel = ViewModelProvider(this).get(CryptomonedasViewModel::class.java)
        cryptomonedasViewModel.onCreate()

        cryptomonedasViewModel.CryptoList.observe(this){

        }




    }
}