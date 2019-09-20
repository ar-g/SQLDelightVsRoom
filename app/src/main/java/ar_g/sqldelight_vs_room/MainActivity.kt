package ar_g.sqldelight_vs_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import e.ar_g.room.RoomActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        delight.setOnClickListener {
            startActivity(Intent(this, e.ar_g.delight.DelightActivity::class.java))
        }

        room.setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }
    }
}
