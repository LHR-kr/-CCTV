package com.example.catcha

// 결과값(통신 성공/실패 여부) 반환
data class PostResult(
    var result:String? = null
)

// 회원가입 정보 보내기
data class UserRegister(
    var birth : String,
    var email : String,
    var id : String,
    var name : String,
    var password : String,
    var phone : String,
    var tokenFCM : String
)

// 로그인 정보 보내기
data class UserLogin(
    var userId : String,
    var userPassword : String
)