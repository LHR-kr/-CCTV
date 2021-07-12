package com.example.catcha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catcha.databinding.UserSidepageBinding

class UserSidePage : AppCompatActivity() {
    private var mBinding: UserSidepageBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = UserSidepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.backX.setOnClickListener{
            finish()
        }

        binding.liveVideo.setOnClickListener{
            val intent = Intent(this, VideoLive::class.java)
            startActivity(intent)
        }

        binding.videoList.setOnClickListener{
            val intent = Intent(this, VideoList::class.java)
            startActivity(intent)
        }

        binding.allStock.setOnClickListener{
            val intent = Intent(this, StockAll::class.java)
            startActivity(intent)
        }

        binding.dpStock.setOnClickListener{
            val intent = Intent(this, StockDp::class.java)
            startActivity(intent)
        }
    }
}