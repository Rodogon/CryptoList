package cl.desafiolatam.cryptolist.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.cryptolist.R
import cl.desafiolatam.cryptolist.data.database.CryptoEntity
import cl.desafiolatam.cryptolist.databinding.ItemCryptomonedasBinding
import com.squareup.picasso.Picasso
import java.util.*

class CryptoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCryptomonedasBinding.bind(view)
    fun bind(cryptoEntity: CryptoEntity){
        binding.tvcryptomoneda.text = cryptoEntity.symbol
        binding.tvvalor.text = cryptoEntity.priceUsd
        Picasso.get().load(getImageFromSymbol(cryptoEntity.symbol)).into(binding.ivbitcoin)
    }

    companion object {
        fun create(parent: ViewGroup): CryptoViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cryptomonedas, parent, false)
            return CryptoViewHolder(view)
        }
    }
}

private fun getImageFromSymbol(symbol: String?): String {
    return "https://static.coincap.io/assets/icons/${symbol?.lowercase(Locale.ROOT)}@2x.png"
}