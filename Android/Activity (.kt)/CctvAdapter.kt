package com.example.catcha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CctvAdapter(val cctvList: ArrayList<Cctvs>) : RecyclerView.Adapter<CctvAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CctvAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_list_item, parent, false)
        return CustomViewHolder(view)
    }


    override fun onBindViewHolder(holder: CctvAdapter.CustomViewHolder, position: Int) {
        holder.pic.setImageResource(cctvList.get(position).pic)
        holder.num.text = cctvList.get(position).num.toString()
        holder.date.text = cctvList.get(position).date.toString()
    }

    override fun getItemCount(): Int {
        return cctvList.size
    }



    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val pic = itemView.findViewById<ImageView>(R.id.cctv_pic) // cctv 캡쳐사진
        val num = itemView.findViewById<TextView>(R.id.cctv_num) // 몇 번 cctv
        val date = itemView.findViewById<TextView>(R.id.cctv_date) // cctv 포착 시간
    }
}