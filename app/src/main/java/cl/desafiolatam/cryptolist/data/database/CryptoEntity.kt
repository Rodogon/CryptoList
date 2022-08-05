package cl.desafiolatam.cryptolist.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "cryptomonedas_db")
data class CryptoEntity(
    val symbol: String?,
    val volumeUsd24Hr: String?,
    val marketCapUsd: String?,
    val priceUsd: String?,
    val vwap24Hr: String?,
    val changePercent24Hr: String?,
    val name: String?,
    val explorer: String?,
    val rank: String?,
    @PrimaryKey
    val id: String,
    val maxSupply: String?,
    val supply: String?
): Serializable
/*@Entity (tableName = "cryptomonedas_db")
class CryptoEntity(
    @Embedded
    val dataCrypto: ArrayList<DataCrypto>,
    val timestamp: Long
)
 */