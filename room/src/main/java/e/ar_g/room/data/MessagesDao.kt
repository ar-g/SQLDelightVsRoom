package e.ar_g.room.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDao {

    @Insert
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM messages ORDER BY payload ASC")
    fun messages() : Flow<List<Message>>

    @Query("""SELECT messages.id AS messageId, payload AS msg, medias.url
  FROM messages
  INNER JOIN medias ON messages.media_id == medias.id
  ORDER BY payload ASC""")
    fun messagesWithMedia() : Flow<List<MessageWithMedia>>
}