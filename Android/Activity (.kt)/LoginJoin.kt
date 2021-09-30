package com.catchyou.catcha

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.catchyou.catcha.databinding.LoginJoinBinding
import com.catchyou.catcha.databinding.UserSidepageBinding
import com.mongodb.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NumberFormatException

class LoginJoin : AppCompatActivity() {
    val binding by lazy { LoginJoinBinding.inflate(layoutInflater) }
    val api = APIS.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        /* 비밀번호 일치하지 않을때 경고문 노출 if - VISIBLE 설정하는거 어떻게하는지..
        if (binding.inputPw.text.toString() != binding.inputPwpw.text.toString()) {
            TextView.visibility = binding.inputPwpwWarn.VISIBLE
        }*/

        // 비밀번호 일치 확인
        binding.inputPwpwCheck.setOnClickListener{
            val regex = "([a-zA-Z]{1,}|[0-9]{1,})]".toRegex()
            var inputpw1 = binding.inputPw.getText().toString()
            var inputpw2 = binding.inputPwpw.getText().toString()

            if (inputpw1.matches(regex) || inputpw2.matches(regex) || inputpw1 != inputpw2) {
                binding.inputPwpwWarn.setText("비밀번호를 확인해주세요.")
            } else {
                binding.inputPwpwWarn.setText("비밀번호가 일치합니다.")
            }


        }

        // 맨 밑 회원가입 버튼
        binding.loginJoinFin.setOnClickListener{
            val data = userRegister(binding.inputBirth.text.toString(), binding.inputEmail.text.toString(), binding.inputId.text.toString(), binding.inputName.text.toString(), binding.inputPw.text.toString(), binding.inputPhone.text.toString(), binding.inputToken.text.toString())
            api.register_users(data).enqueue(object : Callback<postResult> {
                override fun onResponse(call: Call<postResult>, response: Response<postResult>) {
                    Log.d("log",response.toString())
                    Log.d("log", response.body().toString())
                    if(!response.body().toString().isEmpty())
                        binding.text.setText(response.body().toString());
                }

                override fun onFailure(call: Call<postResult>, t: Throwable) {
                    // 실패
                    Log.d("log",t.message.toString())
                    Log.d("log","fail")
                }
            })

            val intent = Intent(this, LoginCctv::class.java)
            startActivity(intent)
        }
    }

    /*
    fun birth(Birthyear: EditText, Birthmon:EditText, Birthday:EditText):String {

    String inputBirth = Birthyear + '-' + Birthmon + '-' + Birthday;

    }*/
}