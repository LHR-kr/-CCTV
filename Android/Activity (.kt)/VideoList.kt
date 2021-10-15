package com.catchyou.catcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.catchyou.catcha.databinding.StockAllBinding
import com.catchyou.catcha.databinding.VideoListBinding

class VideoList : AppCompatActivity() {
    val binding by lazy { VideoListBinding.inflate(layoutInflater) }

    //adapter객체 먼저 선언해주기!
    private lateinit var adapter : VideoListAdapter

    val mDatas = mutableListOf<VideoListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.userSidePage.setOnClickListener{
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        }

        initializelist()
        initVideoListRecyclerView()


    }

    fun initVideoListRecyclerView(){
        val adapter = VideoListAdapter() //어댑터 객체 만듦
        adapter.datalist=mDatas //데이터 넣어줌
        binding.recyclerViewVideo.adapter=adapter //리사이클러뷰에 어댑터 연결
        binding.recyclerViewVideo.layoutManager= LinearLayoutManager(this) //레이아웃 매니저 연결
    }

    fun initializelist() { //임의로 데이터 넣어서 만들어봄
        with(mDatas) {
            add(VideoListData(0, "이브와가게", "2021-10-16"))
            add(VideoListData(0, "이브와가게", "2021-10-15"))
            add(VideoListData(0, "한이음가게", "2021-10-14"))
            add(VideoListData(0, "프로보노가게", "2021-10-10"))
            add(VideoListData(0, "한이음가게", "2021-10-01"))
            add(VideoListData(0, "이브와가게", "2021-09-28"))
            add(VideoListData(0, "프로보노가게", "2021-09-26"))
            add(VideoListData(0, "이브와가게", "2021-09-03"))
            add(VideoListData(0, "한이음가게", "2021-09-01"))
            add(VideoListData(0, "이브와가게", "2021-08-26"))
        }

    }

}