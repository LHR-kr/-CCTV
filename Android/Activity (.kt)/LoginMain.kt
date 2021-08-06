package com.example.catcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catcha.databinding.LoginJoinBinding
import com.example.catcha.databinding.LoginMainBinding

class LoginMain : AppCompatActivity() {
    private var mBinding: LoginMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginJoin.setOnClickListener{
            val intent = Intent(this, LoginJoin::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}