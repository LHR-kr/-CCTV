package com.catchyou.catcha

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.catchyou.catcha.databinding.TestClipBinding
import com.catchyou.catcha.databinding.VideoLiveBinding
import java.text.SimpleDateFormat
import java.util.*

class TestClip : AppCompatActivity() {
    val binding by lazy { TestClipBinding.inflate(layoutInflater) }
    val api = APIS.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // sample.mp4 설정
        val uri: Uri = Uri.parse("android.resource://$packageName/raw/clip3")
        binding.videoView2.setVideoURI(uri)

        binding.videoView2.setOnPreparedListener {
            binding.videoView2.start()
        }
        // 유저사이드 버튼
        binding.userSidepage2.setOnClickListener {
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        }

        binding.btnX.setOnClickListener {
            finish()
        }
    }

}
