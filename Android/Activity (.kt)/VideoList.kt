package com.catchyou.catcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catchyou.catcha.databinding.VideoListBinding

class VideoList : AppCompatActivity() {
    private lateinit var binding: VideoListBinding

    lateinit var videoListAdapter: VideoListAdapter
    val datas = mutableListOf<VideoItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecycler()

        binding.userSidePage.setOnClickListener{
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        }


    }

    private fun initRecycler() {
        videoListAdapter = VideoListAdapter(this)
        binding.itemsVideo.adapter = videoListAdapter


        datas.apply {
            add(VideoItemData(videoPic = R.drawable.sample1, videoName = "한이음", videoDate = "2021-01-01"))
            add(VideoItemData(videoPic = R.drawable.sample2, videoName = "이브와", videoDate = "2021-01-01"))
            add(VideoItemData(videoPic = R.drawable.sample1, videoName = "프로보노", videoDate = "2021-01-01"))
            add(VideoItemData(videoPic = R.drawable.sample3, videoName = "한이음", videoDate = "2021-01-01"))
            add(VideoItemData(videoPic = R.drawable.sample2, videoName = "이브와", videoDate = "2021-01-01"))

            videoListAdapter.datas = datas
            videoListAdapter.notifyDataSetChanged()

        }
    }

}