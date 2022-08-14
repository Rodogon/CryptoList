package cl.desafiolatam.cryptolist.ui.view

import cl.desafiolatam.cryptolist.data.database.CryptoEntity

interface OnItemClickListener {

    fun onItemClickListener(crypto: CryptoEntity)
}