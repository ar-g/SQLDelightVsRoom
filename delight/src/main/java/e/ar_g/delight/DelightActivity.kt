package e.ar_g.delight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import e.ar_g.delight.ui.JoinAdapter
import kotlinx.android.synthetic.main.activity_delight.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class DelightActivity : AppCompatActivity() {

    private val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delight)
        val adapter = JoinAdapter()
        rvMessages.layoutManager = LinearLayoutManager(this)
        rvMessages.adapter = adapter

        val androidSqliteDriver = AndroidSqliteDriver(DroidconDb.Schema, applicationContext, "db.db", callback = object : SupportSQLiteOpenHelper.Callback(2) {
            override fun onCreate(db: SupportSQLiteDatabase?) {}
            override fun onUpgrade(db: SupportSQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
        })
        val db = DroidconDb(androidSqliteDriver)


        btnSendMessage.setOnClickListener {
            uiScope.launch {
                launch(Dispatchers.IO){
                    val mediaId = System.currentTimeMillis()
                    db.messageQueries.saveMessage(etMessage.text.toString(), System.currentTimeMillis(), mediaId)
                    db.mediaQueries.saveMedia(mediaId, "http://media.url.com/$mediaId")
                }
            }
        }

        uiScope.launch {
            db.messageQueries.messageWithMedia()
                .asFlow()
                .mapToList(Dispatchers.IO)
                .collect {
                    adapter.submitList(it)
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        uiScope.cancel()
    }
}
