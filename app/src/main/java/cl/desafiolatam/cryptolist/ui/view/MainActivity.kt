package cl.desafiolatam.cryptolist.ui.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import cl.desafiolatam.cryptolist.CryptoListApp
import cl.desafiolatam.cryptolist.R
import cl.desafiolatam.cryptolist.data.database.CryptoEntity
import cl.desafiolatam.cryptolist.databinding.ActivityMainBinding
import cl.desafiolatam.cryptolist.ui.fragment.CryptoDetail
import cl.desafiolatam.cryptolist.ui.viewmodel.CryptoViewModel
import cl.desafiolatam.cryptolist.ui.viewmodel.CryptoViewModelFactory


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
class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    //private lateinit var listener: OnItemClickListener
    private val fragmentManager = supportFragmentManager
    private val cryptoViewModel: CryptoViewModel by viewModels{
        CryptoViewModelFactory((application as CryptoListApp).repository)
    }

    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_USER_NAME = "username"
    private val SHARED_NAME = "SharedBD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initRecyclerView()
        initSharedPreferences()

        val recyclerView = binding.rvcrypto
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CryptoAdapter(this)
        recyclerView.adapter = adapter

        cryptoViewModel.allCrypto.observe(this){
                crypto -> crypto.let { adapter.submitList(it) }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            cryptoViewModel.getUpdatedData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initSharedPreferences() {
        sharedPreferences = getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        addAfterEdit()
        binding.etname.setText(sharedPreferences.getString(SHARED_USER_NAME, ""))

    }

    private fun addAfterEdit() {
        binding.etname.doAfterTextChanged {
            sharedPreferences.edit().putString(SHARED_USER_NAME, it.toString()).apply()
        }
    }

    /*private fun initRecyclerView() {
        val recyclerView = binding.rvcrypto
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CryptoAdapter(listener)
        recyclerView.adapter = adapter
        cryptoViewModel.allCrypto.observe(this){
            crypto -> crypto.let { adapter.submitList(it, this) }
        }
    }
     */

    override fun onItemClickListener(crypto: CryptoEntity) {

        val bundle = Bundle()
        bundle.putSerializable("cryptoDetail", crypto)

        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = CryptoDetail()
        fragment.arguments = bundle

        fragmentTransaction.addToBackStack("detail_f")
        fragmentTransaction.add(R.id.fgcontainer, fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }


}