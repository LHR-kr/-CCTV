package com.example.catcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catcha.databinding.StockAllBinding

class StockAll : AppCompatActivity() {
    private var mBinding: StockAllBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = StockAllBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userSidePage.setOnClickListener({
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        })
    }
}