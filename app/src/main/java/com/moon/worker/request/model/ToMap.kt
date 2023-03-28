package com.moon.worker.request.model
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.reflect.TypeToken

/**
 * @Des：
 * @author: moon
 * @date: 3/28/23
 */
inline fun <reified T : Any> T.toMap1(): Map<String, kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object> {
    val json = Gson().toJson(this)
    val type = object : TypeToken<Map<String, Any>>() {}.type
    return Gson().fromJson(json, type)
}






