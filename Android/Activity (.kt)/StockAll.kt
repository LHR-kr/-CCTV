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
            add(StockAllCardData(0,"1","20",))
            add(StockAllCardData(0,"20","400",))
            add(StockAllCardData(0,"5","5",))
            add(StockAllCardData(0,"3","12",))
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
            add(StockAllData("수박", "30", "10", "AAAA-8888"))
            add(StockAllData("메론", "5", "200", "SSSS-6666"))
            add(StockAllData("수박", "30", "10", "AAAA-8888"))
            add(StockAllData("메론", "5", "200", "SSSS-6666"))
            add(StockAllData("수박", "30", "10", "AAAA-8888"))
            add(StockAllData("메론", "5", "200", "SSSS-6666"))
            add(StockAllData("수박", "30", "10", "AAAA-8888"))
            add(StockAllData("메론", "5", "200", "SSSS-6666"))
            add(StockAllData("수박", "30", "10", "AAAA-8888"))
            add(StockAllData("메론", "5", "200", "SSSS-6666"))
            add(StockAllData("수박", "30", "10", "AAAA-8888"))
            add(StockAllData("메론", "5", "200", "SSSS-6666"))
            add(StockAllData("수박", "30", "10", "AAAA-8888"))
            add(StockAllData("메론", "5", "200", "SSSS-6666"))
            add(StockAllData("수박", "30", "10", "AAAA-8888"))
            add(StockAllData("메론", "5", "200", "SSSS-6666"))
        }

    }
}