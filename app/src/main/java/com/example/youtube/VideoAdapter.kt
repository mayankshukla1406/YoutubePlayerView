package com.example.youtube

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(val context : Context,val itemList : ArrayList<VideoModal>):RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    class VideoViewHolder(view : View):RecyclerView.ViewHolder(view)
    {
        val content : CardView = view.findViewById(R.id.videoContent)
        val name : TextView = view.findViewById(R.id.txtVideoName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view1 = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_video,parent,false)
        return VideoViewHolder(view1)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val flag = itemList[position]
        holder.name.text = flag.videoName
        holder.content.setOnClickListener{
            val intent = Intent(context,VideoActivity::class.java)
            intent.putExtra("link",flag.videoLink)
            intent.putExtra("name",flag.videoName)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}