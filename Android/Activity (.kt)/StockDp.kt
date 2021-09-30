package com.catchyou.catcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.catchyou.catcha.databinding.StockDpBinding

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

        binding.userSidePage.setOnClickListener{
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        }

        binding.dpMenu.setOnClickListener{
            // 메뉴형으로 배열 변경
        }

        binding.dpList.setOnClickListener{
            // 목록형으로 배열 변경
        }

    }

}