package com.moon.worker.request

import com.moon.worker.request.model.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Des：
 * @author: moon
 * @date: 3/11/23
 */
object RequestManager {

    private var serviceApi: RequestServiceApi? = null
    private var accountApi: AccountApi? = null

    val retrofit = Retrofit.Builder()
        .baseUrl(RequestConstant.DEBUG_BASE_URL)
//        .baseUrl(RequestConstant.BASE_URL)
        .client(getRecipeOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun getAccountApi() : AccountApi{
        return retrofit.create(AccountApi::class.java)
    }

    private fun getRecipeOkHttpClient(): OkHttpClient {
        val level = HttpLoggingInterceptor.Level.BODY
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            println(message)
        }
        loggingInterceptor.level = level

        val httpClientBuilder = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(AuthInterceptor())
        }
        return httpClientBuilder.build()
    }

    // 手机号密码登录
    suspend fun login(
        phoneNumber: String,
        pwd: String,
        smsCode: String? = null
    ): LinqResponse<LoginResponse> {
        val body = LoginReq(
            phoneNumber,pwd,smsCode
        )
        return safeApiCall{
            getAccountApi().login(body)
        }
    }

    // 发送验证码
    suspend fun sendCode(phoneNumber: String,
      type: SendCodeType
    ) : LinqResponse<String> {
        return safeApiCall {
            val map = mapOf<String,String>(
                "phone_number" to phoneNumber,
                "verify_type" to type.value.toString()
            )
            getAccountApi().sendCode(map)
        }
    }
}

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("auth-token", AccountHelper.token)
            .addHeader("Authorization", "Bearer ${AccountHelper.token}")
            .build()
        return chain.proceed(request)
    }
}

suspend fun<T : Any> safeApiCall(call: suspend () -> LinqResponse<T>): LinqResponse<T> {
    return try {
        call()
    } catch (e: Exception) {
        e.printStackTrace()
//        if (!NetWorkUtils.isConnected(get) || (e is java.net.SocketTimeoutException)) {
        if ((e is java.net.SocketTimeoutException)) {
            return LinqResponse(msg = "当前网络不畅，请检查您的网络设置!", code = 1 , data = null)
        } else {

            return LinqResponse(msg = "请求异常,请稍后重试", code = 1, data = null)
        }
    }
}