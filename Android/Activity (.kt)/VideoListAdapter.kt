package com.catchyou.catcha

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class VideoListAdapter (private val context: Context) : RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {

    var datas = mutableListOf<VideoItemData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.video_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val videoName: TextView = itemView.findViewById(R.id.video_name)
        private val videoDate: TextView = itemView.findViewById(R.id.video_date)
        private val videoPic: ImageView = itemView.findViewById(R.id.video_pic)

        fun bind(item: VideoItemData) {
            Glide.with(itemView).load(item.videoPic).into(videoPic)
            videoName.text = item.videoName
            videoDate.text = item.videoDate


        }
    }


}