package ar_g.sqldelight_vs_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import ar_g.sqldelight_vs_room.ui.DelightMessagesAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.android.paging.QueryDataSourceFactory
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_delight.*

class DelightActivity : AppCompatActivity() {

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delight)
        val adapter = DelightMessagesAdapter()
        rvMessages.layoutManager = LinearLayoutManager(this)
        rvMessages.adapter = adapter

        val androidSqliteDriver = AndroidSqliteDriver(UaMobileDelightDb.Schema, applicationContext, "db.db")
        val db = UaMobileDelightDb(androidSqliteDriver)


        btnSendMessage.setOnClickListener {
            val mediaId = System.currentTimeMillis()
            db.mediaQueries.saveMedia(mediaId, "url$mediaId")
            db.messageQueries.saveMessage(etMessage.text.toString(), System.currentTimeMillis(), mediaId)
        }

        val dataSource = QueryDataSourceFactory(
            queryProvider = db.messageQueries::messageWithMedia,
            countQuery = db.messageQueries.countMessages()
        )

        //pagination
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .setPrefetchDistance(20)
            .build()
        val rxPagedListBuilder = RxPagedListBuilder(dataSource, config)
        disposable = rxPagedListBuilder.buildObservable().subscribe(adapter::submitList)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
