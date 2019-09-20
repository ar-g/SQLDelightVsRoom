package e.ar_g.room.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MediaDao {
    @Insert
    suspend fun insertMedia(media: Media)
}