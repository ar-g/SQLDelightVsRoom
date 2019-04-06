package ar_g.sqldelight_vs_room.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medias")
data class Media(
    @PrimaryKey val id: Long,
    val url: String
)