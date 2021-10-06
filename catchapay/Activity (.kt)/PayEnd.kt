package com.catchyou.catchapay

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.catchyou.catchapay.databinding.PayEndBinding

class PayEnd : AppCompatActivity() {
    // ViewBinding
    private lateinit var binding : PayEndBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        // ViewBinding
        super.onCreate(savedInstanceState)
        binding = PayEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMain.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}