package cl.desafiolatam.cryptolist.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CryptoDao {

    @Query("SELECT * FROM cryptomonedas_db")
    fun getAllCrypto(): LiveData<List<CryptoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(crypto: CryptoEntity)

    @Query("DELETE FROM cryptomonedas_db")
    fun deleteAll()

    @Update
    fun update(crypto: CryptoEntity)
}