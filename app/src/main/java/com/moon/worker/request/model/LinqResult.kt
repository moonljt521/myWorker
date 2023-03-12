package com.moon.worker.request.model

data class LinqResponse<out T : Any>(
    val data: T?,
    val code: Int,
    val msg: String,
) : BaseResponse() {
    override fun toString(): String {
        return "Response[data=$data , code=$code , msg=$msg]"
    }
}