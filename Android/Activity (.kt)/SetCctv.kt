package com.catchyou.catcha

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.catchyou.catcha.databinding.SetCctvBinding

class SetCctv : AppCompatActivity() {
    private var mBinding: SetCctvBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = SetCctvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setCctvFin.setOnClickListener{
            finish()

            Toast.makeText(this, "CCTV 설정 완료!", Toast.LENGTH_LONG).show()

        }



    }
}