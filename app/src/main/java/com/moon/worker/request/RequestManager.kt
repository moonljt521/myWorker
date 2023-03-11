package com.moon.worker.request

import com.moon.worker.request.model.LinqResult
import com.moon.worker.request.model.LoginReq
import com.moon.worker.request.model.LoginResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * @Des：
 * @author: moon
 * @date: 3/11/23
 */
object RequestManager {

    private var serviceApi: RequestServiceApi? = null
    private var accountApi: AccountApi? = null

    val retrofit = Retrofit.Builder()
        .baseUrl(RequestConstant.BASE_URL)
        .client(getRecipeOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // 新服务器接口
    private fun getRecipeServiceAPI(): RequestServiceApi? {
        if (serviceApi == null) {
            serviceApi = retrofit.create(RequestServiceApi::class.java)
        }
        return serviceApi
    }

    private fun getAccountApi() : AccountApi{
        return retrofit.create(AccountApi::class.java)
    }

    private fun getRecipeOkHttpClient(): OkHttpClient {
        val level = HttpLoggingInterceptor.Level.BODY
        val loggingInterceptor = HttpLoggingInterceptor { message ->
//            Timber.i(message)
        }
        loggingInterceptor.level = level

        val httpClientBuilder = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(AuthInterceptor())
        }
        return httpClientBuilder.build()
    }

    // 简单列表
    suspend fun login(
        phoneNumber: String,
        pwd: String,
        smsCode: String
    ): LinqResult<LoginResponse> {
        val body = LoginReq(
            phoneNumber,pwd,smsCode
        )
        return safeApiCall {
            getAccountApi().login(body)
        }
    }

}

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", RequestConstant.Authorization)
            .build()
        return chain.proceed(request)
    }
}

suspend fun <T : Any> safeApiCall(call: suspend () -> T): LinqResult<T> {
    return try {
        LinqResult.Success(call())
    } catch (e: Exception) {
        e.printStackTrace()
//        if (!NetworkUtils.isConnected() || (e is java.net.SocketTimeoutException)) {
        if ( (e is java.net.SocketTimeoutException)) {
            return LinqResult.Error("当前网络不畅，请检查您的网络设置!", 3000)
        } else {
            return LinqResult.Error("请求异常,请稍后重试", 3001)
        }
    }
}