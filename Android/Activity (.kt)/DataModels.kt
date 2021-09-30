package com.catchyou.catcha

// 결과값(통신 성공/실패 여부) 반환
data class postResult(
    var result:String? = null
)

// 회원가입 정보 보내기
data class userRegister(
    var birth : String,
    var email : String,
    var id : String,
    var name : String,
    var password : String,
    var phone : String,
    var tokenFCM : String
)

// 로그인 정보 보내기
data class userLogin(
    var userId : String,
    var userPassword : String
)

data class videoLive(
    var url : String
)