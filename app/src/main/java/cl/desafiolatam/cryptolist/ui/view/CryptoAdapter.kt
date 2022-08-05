package cl.desafiolatam.cryptolist.ui.view


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.TaskStackBuilder.create
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.cryptolist.R
import cl.desafiolatam.cryptolist.data.database.CryptoEntity
import cl.desafiolatam.cryptolist.data.model.Crypto

class CryptoAdapter : ListAdapter<CryptoEntity, CryptoViewHolder>(CryptoComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
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
