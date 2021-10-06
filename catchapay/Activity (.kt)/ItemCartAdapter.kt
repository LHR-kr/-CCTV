package com.catchyou.catchapay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catchyou.catchapay.databinding.ItemCartBinding

class ItemCartAdapter : RecyclerView.Adapter<Holder>(){

    var listData = mutableListOf<ItemData>()

    // item_cart.xml 를 써서 해당 자리에 뷰를 띄울 예정이다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val member = listData[position]
        holder.setData(member, position)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

}

class Holder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root){

    private val cart = Cart.getInstance()
    var mItem: ItemData? = null
    var mPosition: Int? = null

    init {
        binding.btnDel.setOnClickListener {
            cart?.deleteItem(mItem!!)
        }

        binding.btnEdit.setOnClickListener {
            cart?.editMember(mPosition!!, mMember!!)
        }
    }

    fun setData(item: ItemData, position: Int){

        binding.itemNum.text = item.chocopie
        binding.itemNum.text = item.frenchpie
        binding.itemNum.text = item.margaret
        binding.itemNum.text = item.moncher

        this.mItem = item
        this.mPosition = position
    }
}