package e.ar_g.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val payload: String,
    val creation_time: Long,
    val media_id: Long
)