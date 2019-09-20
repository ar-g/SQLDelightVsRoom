package e.ar_g.room.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(
    entities = [Message::class, Media::class],
    version = 2,
    exportSchema = false
)
abstract class DroidconDb : RoomDatabase() {
    companion object {
        fun create(context: Context): DroidconDb {
            return Room.databaseBuilder(context, DroidconDb::class.java, "db.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun messagesDao(): MessagesDao

    abstract fun mediaDao(): MediaDao
}