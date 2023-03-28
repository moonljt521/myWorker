package com.moon.worker.request

import com.moon.worker.request.model.LinqResponse
import com.moon.worker.request.model.LoginReq
import com.moon.worker.request.model.LoginResponse
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * @Des：
 * @author: moon
 * @date: 3/11/23
 */
interface AccountApi : RequestServiceApi {

    // 登录
    @POST("user/login")
    suspend fun login(@Body body: LoginReq): LinqResponse<LoginResponse>

    // 发送验证码
    @GET("sms/get_sms_code")
    suspend fun sendCode(@QueryMap body: Map<String,String>): LinqResponse<String>
}