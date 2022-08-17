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


class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
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