package com.example.catcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catcha.databinding.LoginJoinBinding
import com.example.catcha.databinding.UserSidepageBinding

class LoginJoin : AppCompatActivity() {
    private var mBinding: LoginJoinBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LoginJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginJoin.setOnClickListener{
            val intent = Intent(this, LoginMain::class.java)
            startActivity(intent)
        }

    }
}