package com.example.youtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class VideoActivity : AppCompatActivity() {
    private lateinit var youtubePlayer : YouTubePlayerView
    private lateinit var videoTitle : TextView
    private lateinit var link : String
    private lateinit var name : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        if (intent != null) {
            link = intent.getStringExtra("link").toString()
            name = intent.getStringExtra("name").toString()
            youtubePlayer = findViewById(R.id.youtubePlayer)
            videoTitle = findViewById(R.id.txtVideoTitle)
            videoTitle.text = name
            youtubePlayer.initialize("@string/api_key",
                object : YouTubePlayer.OnInitializedListener {
                    override fun onInitializationSuccess(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubePlayer?,
                        p2: Boolean
                    ) {
                        p1?.loadVideo(link)
                        p1?.play()
                    }

                    override fun onInitializationFailure(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubeInitializationResult?
                    ) {
                        Toast.makeText(
                            this@VideoActivity,
                            "Failure to load video",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                })
        }
    }
}