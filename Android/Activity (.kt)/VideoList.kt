package com.catchyou.catcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catchyou.catcha.databinding.ActivityMainBinding
import com.catchyou.catcha.databinding.VideoListBinding

class VideoList : AppCompatActivity() {
    private lateinit var binding: VideoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*mBinding = VideoListBinding.inflate(layoutInflater)
        setContentView(binding.root)*/

        /*binding.userSidePage.setOnClickListener{
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        }*/

        /*val cctvList = arrayListOf(
            // 지금은 예시로 써놓은거고 나중에 서버에서 받아들인 데이터가 표시되도록 바꿔야함
            Cctvs(R.drawable.sample2, 2, 20210708),
            Cctvs(R.drawable.sample1, 3, 20210101),
            Cctvs(R.drawable.sample3, 1, 20200517),
            Cctvs(R.drawable.sample2, 6, 20190708),
            Cctvs(R.drawable.sample2, 2, 20210805),
            Cctvs(R.drawable.sample3, 3, 202190609),
            Cctvs(R.drawable.sample1, 4, 20080612),
            )*/

        /*binding.items_cctv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.items_cctv.setHasFixedSize(true)

        binding.items_cctv.adapter = CctvAdapter(cctvList)*/

        binding = VideoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addData()
        binding.itemsCctv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //binding.itemsCctv.adapter = CctvAdapter(cctvList)
        binding.itemsCctv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        binding.userSidePage.setOnClickListener{
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        }
    }

    private fun addData() {
        // cctv_pic, cctv_market, cctv_date 데이터를 ViewHolder에 삽입하는 코드

    }
}