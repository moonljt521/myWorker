package com.moon.worker.request.model

sealed class LinqResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : LinqResult<T>()
    data class Error(val errorMsg: String, val errorCode: Int) : LinqResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "$errorMsg"
        }
    }
}