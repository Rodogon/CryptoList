package cl.desafiolatam.cryptolist.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.cryptolist.data.model.Crypto
import cl.desafiolatam.cryptolist.data.model.Data
import cl.desafiolatam.cryptolist.databinding.ItemCryptomonedasBinding
import com.squareup.picasso.Picasso
import java.util.*

class CryptoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCryptomonedasBinding.bind(view)
    fun bind(data: Data){
        binding.tvcryptomoneda.text = data.symbol
        binding.tvvalor.text = data.priceUsd
        Picasso.get().load(getImageFromSymbol(data.symbol)).into(binding.ivbitcoin)
    }

}

private fun getImageFromSymbol(symbol: String): String {
    return "https://static.coincap.io/assets/icons/${symbol.lowercase(Locale.ROOT)}@2x.png"
}