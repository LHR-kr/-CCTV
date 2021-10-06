package com.catchyou.catchapay

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.catchyou.catchapay.databinding.CartBinding

class Cart : AppCompatActivity() {
    // ViewBinding
    private lateinit var binding : CartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding
        binding = CartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 상품명 스피너
        val spinner: Spinner = binding.goodsSpinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.goods_array,
            R.layout.goods_spinner
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }



        binding.btnPayend.setOnClickListener{

            // 추가한 상품들 서버에 보내는 코드 추가하기

            val intent = Intent(this, PayEnd::class.java)
            startActivity(intent)
        }

    }
}