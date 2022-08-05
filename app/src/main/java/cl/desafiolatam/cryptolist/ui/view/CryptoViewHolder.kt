package cl.desafiolatam.cryptolist.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
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

}

private fun getImageFromSymbol(symbol: String?): String {
    return "https://static.coincap.io/assets/icons/${symbol?.lowercase(Locale.ROOT)}@2x.png"
}