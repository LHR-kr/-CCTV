package com.example.catcha

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.catcha.databinding.VideoLiveBinding
import android.os.SystemClock
import android.webkit.WebView
import android.widget.ProgressBar
import com.example.catcha.databinding.LoginJoinBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class VideoLive : AppCompatActivity() {
    val binding by lazy { VideoLiveBinding.inflate(layoutInflater) }
    val api = APIS.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



/*
        // 유저사이드 버튼
        binding.userSidepage.setOnClickListener {
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        }
*/

        /* 잠깐만 주석처리 0929
        binding.liveWebview.apply {

            // new WebViewClient()); //클릭시 새창 안뜨게
            webViewClient = WebViewClientClass()


        }*/

        val myWebView: WebView = binding.liveWebview
        myWebView.loadUrl("http://www.example.com")





    }
}

