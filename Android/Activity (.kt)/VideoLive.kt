package com.catchyou.catcha

import android.content.Intent
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.catchyou.catcha.databinding.VideoLiveBinding
import java.text.SimpleDateFormat
import java.util.*


class VideoLive : AppCompatActivity() {
    val binding by lazy { VideoLiveBinding.inflate(layoutInflater) }
    val api = APIS.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // sample.mp4 설정
        val uri: Uri = Uri.parse("android.resource://$packageName/raw/streaming")
        binding.videoView.setVideoURI(uri)

        binding.videoView.setOnPreparedListener {
            // 스트리밍이니까 준비 완료 말해줄 필요 없을듯..
            // Toast.makeText(applicationContext, "동영상 재생 준비 완료", Toast.LENGTH_SHORT).show()
            binding.videoView.start()
        }

/* 완료된거 굳이 보여줄 필요 없으니까..
        binding.videoView.setOnCompletionListener {
            Toast.makeText(applicationContext, "동영상 시청 완료", Toast.LENGTH_SHORT).show()
        }
*/

        // 유저사이드 버튼
        binding.userSidepage.setOnClickListener {
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        }

        // 실시간 시간
        binding.liveTime.setText(getTime())

        /* 잠깐만 주석처리 0929
        binding.liveWebview.apply {

            // new WebViewClient()); //클릭시 새창 안뜨게
            webViewClient = WebViewClientClass()


        }*/

/*
        val myWebView: WebView = binding.liveWebview
        myWebView.loadUrl(url)

        val data = videoLive(binding.inputBirth.text.toString())
        api.register_users(data).enqueue(object : Callback<postResult> {
            override fun onResponse(call: Call<postResult>, response: Response<postResult>) {
                Log.d("log",response.toString())
                Log.d("log", response.body().toString())
                if(!response.body().toString().isEmpty())
                    binding.text.setText(response.body().toString());
            }

            override fun onFailure(call: Call<postResult>, t: Throwable) {
                // 실패
                Log.d("log",t.message.toString())
                Log.d("log","fail")
            }
        })
*/
    }

    fun getTime() : String {
        val now =  System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(now)
        return simpleDateFormat

    }


}