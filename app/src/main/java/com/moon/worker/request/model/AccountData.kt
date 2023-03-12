package com.moon.worker.request.model

import com.google.gson.annotations.SerializedName


data class LoginReq(
    @SerializedName("phone_number")
    val phoneNumber: String,

    @SerializedName("password")
    val password : String,

    @SerializedName("sms_code")
    val smsCode : String? = null
)

// 登录返回实体
data class LoginResponse (
    val id : Int,
    val uid: String,

    @SerializedName("phone_number")
    val phoneNumber : String,
    val accessToken : String,
    val refreshToken : String,

    @SerializedName("twilio_token")
    val twilioToken : String,
) : BaseResponse()