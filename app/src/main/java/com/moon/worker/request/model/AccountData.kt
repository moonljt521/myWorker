package com.moon.worker.request.model


data class LoginReq(
    val phoneNumber: String,
    val password : String,
    val smsCode : String
)

// 登录返回实体
data class LoginResponse (
    val id : Int,
    val uid: String,
    val phoneNumber : String,
    val accessToken : String,
    val refreshToken : String,
    val twilioToken : String,
)