package com.example.youtube

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : YouTubeBaseActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var layoutManager : RecyclerView.LayoutManager
    private lateinit var videoadapter  : VideoAdapter
    val videoInfoList = arrayListOf<VideoModal>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerViewVideos)
        layoutManager = LinearLayoutManager(this)
        FirebaseFirestore.getInstance().collection("Videos").get().addOnSuccessListener {query->
            if(!query.isEmpty)
            {
                val list = query.documents
                for(i in list)
                {
                    val obj = VideoModal(i.get("videoName").toString(),i.get("videoLink").toString())
                    videoInfoList.add(obj)
                    videoadapter = VideoAdapter(this,videoInfoList)
                    recyclerView.adapter = videoadapter
                    recyclerView.layoutManager = layoutManager
                    recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context,(layoutManager as LinearLayoutManager).orientation))
                }
            }
            else
            {
                Toast.makeText(this,"No Data Available",Toast.LENGTH_LONG).show()
            }
        }
    }
}