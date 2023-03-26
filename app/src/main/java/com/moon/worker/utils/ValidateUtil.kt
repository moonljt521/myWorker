package com.moon.worker.utils

/**
 * @Des：
 * @author: moon
 * @date: 3/26/23
 */
class ValidateUtil {

    companion object {

        fun isMobile(mobile : String?) : Boolean {
            if (mobile == null) return false
            return mobile.length >= 10
        }

        fun isPwd(pwd : String?) :Boolean {
            if (pwd == null) return false
            return pwd.length >= 6
        }
    }
}