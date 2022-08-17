package cl.desafiolatam.cryptolist.ui.view



import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import cl.desafiolatam.cryptolist.data.database.CryptoEntity


class CryptoAdapter(private val listener: OnItemClickListener) : ListAdapter<CryptoEntity, CryptoViewHolder>(CryptoComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, listener)
    }

    class CryptoComparator : DiffUtil.ItemCallback<CryptoEntity>(){
        override fun areItemsTheSame(oldItem: CryptoEntity, newItem: CryptoEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CryptoEntity, newItem: CryptoEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }

}
