package ar_g.sqldelight_vs_room.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MessageWithMedia(
    val messageId: Int,
    val msg: String,
    val creationTime: Long,
    val url: String
)