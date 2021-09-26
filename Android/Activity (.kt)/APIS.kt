package com.example.catcha

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface APIS {


    // 로그인 정보 보내기
    @POST("/user/login")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun login_users(
        @Body UserLogin : UserLogin
    ): Call<PostResult>


    // 회원가입 정보 보내기
    @POST("/user/register")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun register_users(
        @Body UserRegister : UserRegister
    ): Call<PostResult>

/*  API에 parameter가 없음..
    @GET("/user/withdrawal")
    @Headers("accept: application/json",
        "content-type: application/json"
    )
    fun get_users(
    ): Call<HTTP_GET_Model>
*/


    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val BASE_URL = "http://44.192.125.145:8080/" // 주소

        fun create(): APIS {


            val gson :Gson =   GsonBuilder().setLenient().create();

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APIS::class.java)
        }
    }
}