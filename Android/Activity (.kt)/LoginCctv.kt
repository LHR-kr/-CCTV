package com.example.catcha

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.catcha.databinding.LoginCctvBinding
import com.example.catcha.databinding.LoginJoinBinding

class LoginCctv : AppCompatActivity() {
    private var mBinding: LoginCctvBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LoginCctvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginJoinFin.setOnClickListener{
            val intent = Intent(this, LoginMain::class.java)
            startActivity(intent)

            Toast.makeText(this, "CCTV 등록 완료!", Toast.LENGTH_LONG).show()

        }



    }
}