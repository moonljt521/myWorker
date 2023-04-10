package com.moon.worker.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * @Des：
 * @author: moon
 * @date: 4/10/23
 */
class SharedPreferencesUtil private constructor(context: Context) {

    init {
        mPreferences = context.getSharedPreferences(mTAG, Context.MODE_PRIVATE)
        mEditor = mPreferences.edit()
    }

    // 存入数据
    fun putSP(key: String, value: String) {
        mEditor.putString(key, value)
        mEditor.commit()
    }

    // 获取数据
    fun getSP(key: String): String? {
        return mPreferences.getString(key, "")
    }

    // 移除数据
    fun removeSP(key: String) {
        mEditor.remove(key)
        mEditor.commit()
    }

    companion object {
        val mTAG = "my_worker"
        // 创建一个写入器
        private lateinit var mPreferences: SharedPreferences
        private lateinit var mEditor: SharedPreferences.Editor
        private var mSharedPreferencesUtil: SharedPreferencesUtil? = null

        // 单例模式
        fun getSPInstance(context: Context): SharedPreferencesUtil {
            if (mSharedPreferencesUtil == null) {
                mSharedPreferencesUtil = SharedPreferencesUtil(context)
            }
            return mSharedPreferencesUtil!!
        }
    }
}
