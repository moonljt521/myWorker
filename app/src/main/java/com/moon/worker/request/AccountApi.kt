package com.moon.worker.request

import com.moon.worker.request.model.LoginReq
import com.moon.worker.request.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @Des：
 * @author: moon
 * @date: 3/11/23
 */
interface AccountApi : RequestServiceApi {

    // 登录
    @POST("user/login")
    suspend fun login(@Body body: LoginReq): LoginResponse
}