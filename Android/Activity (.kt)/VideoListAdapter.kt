package com.catchyou.catcha

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.catchyou.catcha.databinding.StockDpItemBinding
import com.catchyou.catcha.databinding.VideoListItemBinding

class VideoListAdapter : RecyclerView.Adapter<VideoListAdapter.MyViewHolder>() {

    var datalist = mutableListOf<VideoListData>()//리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가

    inner class MyViewHolder(private val binding: VideoListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(videoListData : VideoListData){
            // 이미지 넘기는 방법 찾으면 바꾸기 ㅠ
            binding.videoImg.setImageResource(R.drawable.sample2)

            binding.videoName.text = videoListData.videoName.toString()
            binding.videoDate.text = videoListData.videoDate.toString()
        }
    }


    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding= VideoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }


}