package com.catchyou.catchapay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catchyou.catchapay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // ViewBinding
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCctvid.setOnClickListener{
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }

    }
}