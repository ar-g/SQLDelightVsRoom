package ar_g.sqldelight_vs_room.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(
    entities = [Message::class, Media::class],
    version = 1,
    exportSchema = false
)
abstract class UaMobileRoomDb : RoomDatabase() {
    companion object {
        fun create(context: Context): UaMobileRoomDb {
            return Room.databaseBuilder(context, UaMobileRoomDb::class.java, "db.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun messagesDao(): MessagesDao

    abstract fun mediaDao(): MediaDao
}