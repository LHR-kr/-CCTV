package com.catchyou.catcha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.catchyou.catcha.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //adapter객체 먼저 선언해주기!
    private lateinit var adapter:MainActivityCardAdapter

    val mDatas=mutableListOf<StockAllCardData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.userSidePage.setOnClickListener{
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        }

/*
        binding.liveVideo.setOnClickListener{
            val intent = Intent(this, VideoLive::class.java)
            startActivity(intent)
        }
*/

        binding.videoList.setOnClickListener{
            val intent = Intent(this, VideoList::class.java)
            startActivity(intent)
        }

        binding.allStock.setOnClickListener{
            val intent = Intent(this, StockAll::class.java)
            startActivity(intent)
        }

        binding.dpStock.setOnClickListener{
            val intent = Intent(this, StockDp::class.java)
            startActivity(intent)
        }

        initializeCardlist()
        initMainActivityCardRecyclerView()


    }

    fun initMainActivityCardRecyclerView(){
        val adapter=MainActivityCardAdapter() //어댑터 객체 만듦
        adapter.datalist=mDatas //데이터 넣어줌
        binding.recyclerViewCard.adapter=adapter //리사이클러뷰에 어댑터 연결
        binding.recyclerViewCard.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    } //레이아웃 매니저 연결 (가로 스크롤)

    fun initializeCardlist() { //임의로 데이터 넣어서 만들어봄
        with(mDatas) {
            add(StockAllCardData(0,"1","20",))
            add(StockAllCardData(0,"20","400",))
            add(StockAllCardData(0,"5","5",))
            add(StockAllCardData(0,"3","12",))
        }

    }

}