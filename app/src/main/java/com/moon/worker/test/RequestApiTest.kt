package com.moon.worker.test

import android.util.Log
import com.moon.worker.request.AccountHelper
import com.moon.worker.request.RequestManager
import com.moon.worker.request.model.LinqResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber

/**
 * @Des：
 * @author: moon
 * @date: 3/12/23
 */

class RequestApiTest {

    @Test
    fun testLogin(): Unit = runBlocking {
        val result = RequestManager.login("+11111111119","ljt111111")
        println("testLogin : ${result.toString()}")
        val token = result.data?.accessToken

        if(token?.isNotEmpty() == true){
            AccountHelper.token = token
            println("${result.data.accessToken}")
        }
//        Log.i("moon", result.toString())
    }

    @Test
    fun testDelegrate() {
        val impl = PrintImpl()
        var i = I(impl)
        i.print()
    }

}

interface BasePrint {
    fun print()
}

class PrintImpl : BasePrint {
    override fun print() {
        println("impl....")
    }
}

class I(val impl: BasePrint) : BasePrint by impl {

}