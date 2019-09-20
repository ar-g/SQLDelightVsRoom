package e.ar_g.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_room.*

import androidx.recyclerview.widget.LinearLayoutManager
import e.ar_g.room.data.Media
import e.ar_g.room.data.Message
import e.ar_g.room.data.DroidconDb
import e.ar_g.room.ui.JoinAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class RoomActivity : AppCompatActivity() {

    private val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        val adapter = JoinAdapter()
        rvMessages.layoutManager = LinearLayoutManager(this)
        rvMessages.adapter = adapter


        val db = DroidconDb.create(this)

        uiScope.launch {
            db.messagesDao()
                .messagesWithMedia()
                .collect {
                    adapter.submitList(it)
                }
        }

        btnSendMessage.setOnClickListener {
            uiScope.launch {
                val mediaId = System.currentTimeMillis()
                db.messagesDao().insertMessage(Message(0, etMessage.text.toString(), mediaId, mediaId))
                db.mediaDao().insertMedia(Media(mediaId, "http://media.url.com/$mediaId"))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        uiScope.cancel()
    }
}
