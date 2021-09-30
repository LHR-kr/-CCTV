package com.catchyou.catcha

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.catchyou.catcha.databinding.AlarmListBinding
import com.catchyou.catcha.databinding.VideoLiveBinding

class AlarmList : AppCompatActivity() {
    val binding by lazy { VideoLiveBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}