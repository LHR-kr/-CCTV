package com.example.catcha

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catcha.databinding.ActivityMainBinding
import com.example.catcha.databinding.AlarmListBinding

class AlarmList : AppCompatActivity() {
    private var mBinding: AlarmListBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = AlarmListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}