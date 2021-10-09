package com.catchyou.catcha

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.catchyou.catcha.databinding.LoginJoinBinding
import com.catchyou.catcha.databinding.LoginMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginMain : AppCompatActivity() {
    val binding by lazy { LoginMainBinding.inflate(layoutInflater) }
    val api = APIS.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.loginJoin.setOnClickListener{
            val intent = Intent(this, LoginJoin::class.java)
            startActivity(intent)
        }

/*
        // 로그인 버튼 - 회원 확인 여부 적용
        binding.login.setOnClickListener{
            val data = userLogin(binding.logininputId.text.toString(), binding.logininputPw.text.toString())
            api.login_users(data).enqueue(object : Callback<PostResult> {
                override fun onResponse(call: Call<postResult>, response: Response<postResult>) {
                    Log.d("log",response.toString())
                    Log.d("log", response.body().toString())
*/
/*
                    if(!response.body().toString().isEmpty())
                        binding.text.setText(response.body().toString());
*//*

                }

                override fun onFailure(call: Call<PostResult>, t: Throwable) {
                    // 실패
                    Log.d("log",t.message.toString())
                    Log.d("log","fail")

*/
/* 실패했을때 토스트메시지
                    Toast.makeText(this, "아이디 또는 비밀번호를 확인해주세요", Toast.LENGTH_LONG).show()
*//*

                }

            })
        }
*/

        // 로그인 버튼 - 단순 화면 넘어가기
        binding.login.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = token.toString()
            Log.d("FCM", msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })




    }

}