package ar_g.sqldelight_vs_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.paging.*
import androidx.recyclerview.widget.LinearLayoutManager
import ar_g.sqldelight_vs_room.room.Media
import ar_g.sqldelight_vs_room.room.Message
import ar_g.sqldelight_vs_room.room.UaMobileRoomDb
import ar_g.sqldelight_vs_room.ui.RoomMessagesAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.android.paging.QueryDataSourceFactory
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        delight.setOnClickListener {
            startActivity(Intent(this, DelightActivity::class.java))
        }

        room.setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }
    }
}
