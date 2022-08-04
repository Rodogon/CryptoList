package cl.desafiolatam.cryptolist.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.cryptolist.R
import cl.desafiolatam.cryptolist.data.model.Crypto
import cl.desafiolatam.cryptolist.data.model.Data
import cl.desafiolatam.cryptolist.databinding.ItemCryptomonedasBinding

class CryptoAdapter(private val cryptoList: ArrayList<Data>) : RecyclerView.Adapter<CryptoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cryptomonedas, parent, false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val item = cryptoList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = cryptoList.size

}