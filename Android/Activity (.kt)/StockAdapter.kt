package com.example.catcha

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catcha.databinding.StockAllItemBinding
import com.example.catcha.databinding.VideoListItemBinding

class StockAdapter (private var stockList: ArrayList<List<Stocks>>) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockAdapter.ViewHolder {
        val binding = StockAllItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StockAdapter.ViewHolder, position: Int) {
        holder.bind(stockList[position])
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    class ViewHolder(private val binding: StockAllItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: List<Stocks>) {
            //image는 왜 다 text 오류가 나는거야 ㅠㅠ
            //binding.stockPic.text = data[0]
            binding.stockDp.text = data[1].toString()
            binding.stockHome.text = data[2].toString()
        }
    }
}