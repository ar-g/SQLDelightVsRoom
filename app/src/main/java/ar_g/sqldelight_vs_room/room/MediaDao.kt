package ar_g.sqldelight_vs_room.room

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MediaDao {
    @Insert
    fun insertMedia(media: Media)
}