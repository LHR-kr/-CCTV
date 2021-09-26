package com.example.catcha

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.catcha.databinding.VideoLiveBinding
import android.os.SystemClock
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class VideoLive : AppCompatActivity() {
    private var mBinding: VideoLiveBinding? = null
    private val binding get() = mBinding!!

    private var isRunning=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = VideoLiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val thread=ThreadClass()
        thread.start()

        binding.userSidePage.setOnClickListener {
            val intent = Intent(this, UserSidePage::class.java)
            startActivity(intent)
        }

        // thread로 묶어서 백그라운드에서 실행되게하기
        thread(start = true) {
            // URL 주소에 대한 객체 생성
            val githubEndpoint = URL("https://api.github.com/")

            // openConnection() 메서드를 호출하여 연결을 생성, 이 메서드는 추상 HttpURLConnection이라는 구현 클래스로 형변환하여 변수에 담아둠
            // requestMethod 속성을 통해 요청방식 설정
            val myConnection = githubEndpoint.openConnection() as HttpURLConnection
            myConnection.requestMethod = "GET"

            // responseCode로 연결 성공 여부 판단
            if (myConnection.responseCode == HttpURLConnection.HTTP_OK) {
            }

            // 연결 성공시 입력스트림을 생성하여 버퍼에 담아둠
            if (myConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val streamReader = InputStreamReader(myConnection.inputStream)
                val buffered = BufferedReader(streamReader)
            }

            // 버퍼를 통해 읽어온 데이터를 한줄씩 문자열로 저장
            if (myConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val streamReader = InputStreamReader(myConnection.inputStream)
                val buffered = BufferedReader(streamReader)

                val content = StringBuilder()
                while (true) {
                    val data = buffered.readLine() ?: break
                    content.append(data)
                }
            }

            // 데이터를 모두 읽었으면 버퍼와 연결을 모두 닫고 읽은 데이터를 Log를 통해 출력
            if (myConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val streamReader = InputStreamReader(myConnection.inputStream)
                val buffered = BufferedReader(streamReader)

                val content = StringBuilder()
                while (true) {
                    val data = buffered.readLine() ?: break
                    content.append(data)
                }

                buffered.close()
                myConnection.disconnect()

                Log.d("결과값", "${content}")
            }
        }

        // 블로그 글 보고 하기. 일단 보류...


    }

    inner class ThreadClass:Thread(){
        override fun run(){
            while(isRunning){
                SystemClock.sleep(100)
                Log.d("쓰레드",System.currentTimeMillis().toString())
            }




        }
    }
    override fun onDestroy() {
        super.onDestroy()
        isRunning=false
    }

}

