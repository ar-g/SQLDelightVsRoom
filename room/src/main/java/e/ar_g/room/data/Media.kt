package e.ar_g.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medias")
data class Media(
    @PrimaryKey val id: Long,
    val url: String
)