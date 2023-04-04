package com.moon.worker.ui.account.login_verify_code_fill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moon.worker.request.AccountHelper
import com.moon.worker.request.RequestManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @Des：
 * @author: moon
 * @date: 3/28/23
 */
class VerifyCodeFillViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VerifyCodeData())

    val uiState : StateFlow<VerifyCodeData> = _uiState

    fun enabledButton(code : String) : Boolean{
        return code.length >= 6
    }

    fun emitCode(code: String) {
        viewModelScope.launch {
            _uiState.emit(uiState.value.copy(code = code))
        }
    }

    fun verifyCodeLogin (phoneNumber: String ,loginResult:(Boolean) -> Unit) {
        viewModelScope.launch {
            val code = uiState.value.code
            val result = RequestManager.login("+1$phoneNumber" , smsCode = code)

            println("testLogin : ${result.toString()}")
            val token = result.data?.accessToken
            if (token?.isNotEmpty() == true){
                AccountHelper.token = token
                loginResult(true)
            }
        }

    }


}

data class VerifyCodeData(
    val code :String = ""
)