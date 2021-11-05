package com.catchyou.catcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.catchyou.catcha.databinding.StockAllBinding

class StockAll : AppCompatActivity() {
    val binding by lazy { StockAllBinding.inflate(layoutInflater) }

    //adapter객체 먼저 선언해주기!
    private lateinit var adapter:StockAllCardAdapter
    private lateinit var adapter2:StockAllAdapter

    val mDatas=mutableListOf<StockAllCardData>()
    val mDatas2=mutableListOf<StockAllData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.userSidePage.setOnClickListener({
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        })

        binding.userSidePage.setOnClickListener{
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        }



        // 카드뷰
        initializeCardlist()
        initStockAllCardRecyclerView()

        // 밑에 재고표
        initStockAllRecyclerView()
        initializeStockAlllist()

    }

    fun initStockAllCardRecyclerView(){
        val adapter=StockAllCardAdapter() //어댑터 객체 만듦
        adapter.datalist=mDatas //데이터 넣어줌
        binding.recyclerViewCard.adapter=adapter //리사이클러뷰에 어댑터 연결
        binding.recyclerViewCard.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    } //레이아웃 매니저 연결 (가로 스크롤)

    fun initializeCardlist() { //임의로 데이터 넣어서 만들어봄
        with(mDatas) {
            add(StockAllCardData(0,"6","10",))
            add(StockAllCardData(0,"5","15",))
            add(StockAllCardData(0,"2","20",))
            add(StockAllCardData(0,"7","15",))
        }

    }

    fun initStockAllRecyclerView(){
        val adapter=StockAllAdapter() //어댑터 객체 만듦
        adapter.datalist = mDatas2 //데이터 넣어줌
        binding.recyclerViewStock.adapter=adapter //리사이클러뷰에 어댑터 연결
        binding.recyclerViewStock.layoutManager= LinearLayoutManager(this) //레이아웃 매니저 연결
    }

    fun initializeStockAlllist() { //임의로 데이터 넣어서 만들어봄
        with(mDatas2) {
            add(StockAllData("이브와", "마가레트", "2", "10", "8801051099553"))
            add(StockAllData("프로보노","마가레트", "5", "20", "8801051099553"))
            add(StockAllData("한이음", "마가레트", "1", "35", "8801051099553"))
            add(StockAllData("무명", "마가레트", "2", "40", "8801051099553"))
            add(StockAllData("이브와", "몽쉘", "8", "10", "6924513900067"))
            add(StockAllData("프로보노", "몽쉘", "7", "15","6924513900067"))
            add(StockAllData("한이음", "몽쉘", "3", "10", "6924513900067"))
            add(StockAllData("무명", "몽쉘", "5", "30", "6924513900067"))
            add(StockAllData("이브와", "초코파이", "6", "10", "9791157675807"))
            add(StockAllData("프로보노", "초코파이", "5", "15", "9791157675807"))
            add(StockAllData("한이음", "초코파이", "2", "20", "9791157675807"))
            add(StockAllData("무명", "초코파이", "7", "15","9791157675807"))
            add(StockAllData("이브와", "프렌치파이", "4", "20", "8809629360500"))
            add(StockAllData("프로보노", "프렌치파이", "9", "30", "8809629360500"))
            add(StockAllData("한이음", "프렌치파이", "8", "35", "8809629360500"))
            add(StockAllData("무명", "프렌치파이", "8", "10","8809629360500"))

        }

    }
}