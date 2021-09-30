package com.catchyou.catcha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catchyou.catcha.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userSidePage.setOnClickListener{
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
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