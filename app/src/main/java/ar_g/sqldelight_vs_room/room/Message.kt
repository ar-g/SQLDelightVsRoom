package ar_g.sqldelight_vs_room.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val payload: String,
    val creation_time: Long,
    val media_id: Long
)