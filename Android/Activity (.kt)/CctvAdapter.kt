package com.example.catcha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catcha.databinding.VideoListItemBinding

class CctvAdapter(private var cctvList: ArrayList<List<Cctvs>>) : RecyclerView.Adapter<CctvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /*뷰바인딩 사용중이므로 아래 코드로 바꿈
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_list_item, parent, false)
        return CustomViewHolder(view)*/

        /*val view = ListItemBinding.inflate(LayoutInflater.from(parent), parent, false)
        return RecyclerView.ViewHolder(binding)*/

        /*val view = LayoutInflater.from(parent.context).inflate(R.layout.video_list_item, parent, false)
        return ViewHolder(view)*/

        val binding = VideoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*뷰바인딩 사용중이므로 아래 코드로 바꿈
        holder.pic.setImageResource(cctvList.get(position).pic)
        holder.num.text = cctvList.get(position).num.toString()
        holder.date.text = cctvList.get(position).date.toString()*/
        holder.bind(cctvList[position])
    }

    override fun getItemCount(): Int {
        return cctvList.size
    }



    /*뷰바인딩 사용중이므로 아래 코드로 바꿈
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val pic = itemView.findViewById<ImageView>(R.id.cctv_pic) // cctv 캡쳐사진
        val num = itemView.findViewById<TextView>(R.id.cctv_num) // 몇 번 cctv
        val date = itemView.findViewById<TextView>(R.id.cctv_date) // cctv 포착 시간
    }*/

    /*class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:List<Cctvs>) {
            binding.tvMain.text = data[0]
            binding.tvSub.text = data[1]
        }
    }*/

    class ViewHolder(private val binding: VideoListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:List<Cctvs>) {
            binding.cctvPic.text = data[0].toString()
            binding.cctvMarket.text = data[1].toString()
            binding.cctvDate.text = data[2].toString()
        }
    }
}