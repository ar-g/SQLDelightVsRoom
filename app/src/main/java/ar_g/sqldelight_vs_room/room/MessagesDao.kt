package ar_g.sqldelight_vs_room.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessagesDao {

    @Insert
    fun insertMessage(message: Message)

    @Query("SELECT * FROM messages ORDER BY payload ASC")
    fun messages() : DataSource.Factory<Int, Message>
}