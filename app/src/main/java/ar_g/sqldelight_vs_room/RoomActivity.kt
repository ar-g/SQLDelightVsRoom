package ar_g.sqldelight_vs_room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import ar_g.sqldelight_vs_room.room.Media
import ar_g.sqldelight_vs_room.room.Message
import ar_g.sqldelight_vs_room.room.UaMobileRoomDb
import ar_g.sqldelight_vs_room.ui.RoomJoinMessagesAdapter
import ar_g.sqldelight_vs_room.ui.RoomMessagesAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_delight.*

class RoomActivity : AppCompatActivity() {

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delight)
        val adapter = RoomJoinMessagesAdapter()
        rvMessages.layoutManager = LinearLayoutManager(this)
        rvMessages.adapter = adapter


        val roomDb = UaMobileRoomDb.create(this)
        val roomDataSource = roomDb.messagesDao().messages()

        btnSendMessage.setOnClickListener {
            val mediaId = System.currentTimeMillis()
            roomDb.messagesDao().insertMessage(Message(0, etMessage.text.toString(), mediaId, mediaId))
            roomDb.mediaDao().insertMedia(Media(mediaId, "url$mediaId"))
        }


        //pagination
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .setPrefetchDistance(20)
            .build()
        val rxPagedListBuilder = RxPagedListBuilder(roomDataSource, config)
        disposable = rxPagedListBuilder.buildObservable().subscribe(adapter::submitList)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
