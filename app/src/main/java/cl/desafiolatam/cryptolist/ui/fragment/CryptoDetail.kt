package cl.desafiolatam.cryptolist.ui.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import cl.desafiolatam.cryptolist.R
import cl.desafiolatam.cryptolist.data.database.CryptoEntity
import cl.desafiolatam.cryptolist.databinding.ActivityMainBinding
import cl.desafiolatam.cryptolist.databinding.FragmentCryptoDetailBinding
import com.squareup.picasso.Picasso
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*



/**
 * A simple [Fragment] subclass.
 * Use the [CryptoDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class CryptoDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        assert(arguments != null)
        val crypto = requireArguments()["cryptoDetail"] as CryptoEntity?
        val view = inflater.inflate(R.layout.fragment_crypto_detail, container, false)
        //binding = FragmentCryptoDetailBinding.inflate(inflater)
            //ActivityMainBinding.inflate(layoutInflater)
        val imageView = view.findViewById<ImageView>(R.id.ivbitcoin2)
        val symbol = view.findViewById<TextView>(R.id.tvsymbol)
        val name = view.findViewById<TextView>(R.id.tvname)
        val time = view.findViewById<TextView>(R.id.tvtime)
        val usd = view.findViewById<TextView>(R.id.tvusd2)
        val supply = view.findViewById<TextView>(R.id.tvsupply)
        val marketCap = view.findViewById<TextView>(R.id.tvmarketcap)

        Picasso.get().load(getnewImageFromSymbol(crypto?.symbol)).into(imageView)
        symbol.text = crypto?.symbol
        name.text = crypto?.name
        time.text = LocalTime.now().toString()
        usd.text = crypto?.priceUsd
        supply.text = crypto?.supply
        marketCap.text = crypto?.marketCapUsd


            //Picasso.get().load(getnewImageFromSymbol(crypto?.symbol)).into(binding.ivbitcoin2)



        // Inflate the layout for this fragment
        return view
    }

    fun getnewImageFromSymbol(symbol: String?): String {
        return "https://static.coincap.io/assets/icons/${symbol?.lowercase(Locale.ROOT)}@2x.png"
    }

    companion object {

        // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CryptoDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String?, param2: String?) : CryptoDetail{
            val fragment = CryptoDetail()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }

    }
}