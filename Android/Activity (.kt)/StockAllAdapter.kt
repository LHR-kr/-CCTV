package com.catchyou.catcha

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catchyou.catcha.databinding.StockAllItemBinding

class StockAllAdapter : RecyclerView.Adapter<StockAllAdapter.MyViewHolder>() {
    //리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가
    var datalist = mutableListOf<StockAllData>()


    inner class MyViewHolder(private val binding: StockAllItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(stockAllData:StockAllData){
            binding.stockAllName.text = stockAllData.stockAllName.toString()
            binding.stockAllDpStock.text = stockAllData.stockAllDpStock.toString()
            binding.stockAllAllStock.text = stockAllData.stockAllAllStock.toString()
            binding.stockAllCode.text = stockAllData.stockAllCode.toString()
        }
    }


    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding= StockAllItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int =datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }


}