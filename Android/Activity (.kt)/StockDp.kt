package com.example.catcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catcha.databinding.StockDpBinding

class StockDp : AppCompatActivity() {
    private var mBinding: StockDpBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = StockDpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userSidePage.setOnClickListener({
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        })
    }

}