package com.catchyou.catcha

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.catchyou.catcha.databinding.StockDpBinding

class StockDp : AppCompatActivity() {
    val binding by lazy { StockDpBinding.inflate(layoutInflater) }

    //adapter객체 먼저 선언해주기!
    private lateinit var adapter:StockDpAdapter

    val mDatas = mutableListOf<StockDpData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.userSidePage.setOnClickListener({
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        })


        // 정렬 스피너
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.listset_array,
            R.layout.stock_dp_spinner_listset
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerListset.adapter = adapter
        }


        initializelist()
        initStockDpRecyclerView()

    }

    fun initStockDpRecyclerView(){
        val adapter=StockDpAdapter() //어댑터 객체 만듦
        adapter.datalist=mDatas //데이터 넣어줌
        binding.recyclerView2.adapter=adapter //리사이클러뷰에 어댑터 연결
        binding.recyclerView2.layoutManager= LinearLayoutManager(this) //레이아웃 매니저 연결
    }

    fun initializelist() { //임의로 데이터 넣어서 만들어봄
        with(mDatas) {
            add(StockDpData("사과", "35", "2222-8888"))
            add(StockDpData("바나나", "7", "9999-0000"))
            add(StockDpData("사과", "35", "2222-8888"))
            add(StockDpData("바나나", "7", "9999-0000"))
            add(StockDpData("사과", "35", "2222-8888"))
            add(StockDpData("바나나", "7", "9999-0000"))
            add(StockDpData("사과", "35", "2222-8888"))
            add(StockDpData("바나나", "7", "9999-0000"))
            add(StockDpData("사과", "35", "2222-8888"))
            add(StockDpData("바나나", "7", "9999-0000"))
            add(StockDpData("사과", "35", "2222-8888"))
            add(StockDpData("바나나", "7", "9999-0000"))
            add(StockDpData("사과", "35", "2222-8888"))
            add(StockDpData("바나나", "7", "9999-0000"))
            add(StockDpData("사과", "35", "2222-8888"))
            add(StockDpData("바나나", "7", "9999-0000"))
            add(StockDpData("사과", "35", "2222-8888"))
            add(StockDpData("바나나", "7", "9999-0000"))
            add(StockDpData("사과", "35", "2222-8888"))
            add(StockDpData("바나나", "7", "9999-0000"))
        }

    }
}