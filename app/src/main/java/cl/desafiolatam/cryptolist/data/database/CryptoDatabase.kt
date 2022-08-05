package cl.desafiolatam.cryptolist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CryptoEntity::class], version = 1, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CryptoDatabase? = null

        fun getDatabase(context: Context): CryptoDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoDatabase::class.java,
                    "crypto_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

