package com.example.catcha

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.catcha.databinding.LoginJoinBinding
import com.example.catcha.databinding.UserSidepageBinding

class LoginJoin : AppCompatActivity() {
    private var mBinding: LoginJoinBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LoginJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginJoinFin.setOnClickListener{
            val intent = Intent(this, LoginCctv::class.java)
            startActivity(intent)

            val toast = Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_LONG)
            toast.show()
        }

        /* 비밀번호 일치하지 않을때 경고문 노출 if - VISIBLE 설정하는거 어떻게하는지..
        if (binding.inputPw.text.toString() != binding.inputPwpw.text.toString()) {
            TextView.visibility = binding.inputPwpwWarn.VISIBLE
        }*/

        binding.inputPwpwCheck.setOnClickListener{
            val regex = "([a-zA-Z]{1,}|[0-9]{1,})]".toRegex()
            var inputpw1 = binding.inputPw.getText().toString()
            var inputpw2 = binding.inputPwpw.getText().toString()

            if (inputpw1.matches(regex) || inputpw2.matches(regex) || inputpw1 != inputpw2) {
                binding.inputPwpwWarn.setText("비밀번호를 다시 입력해주세요.")
            } else {
                binding.inputPwpwWarn.setText("비밀번호가 일치합니다.")
            }


        }


    }
}