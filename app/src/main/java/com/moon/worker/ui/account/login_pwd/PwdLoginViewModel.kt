package com.moon.worker.ui.account.login_pwd

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moon.worker.LinqApplication
import com.moon.worker.request.AccountHelper
import com.moon.worker.request.RequestManager
import com.moon.worker.ui.account.AccountUIData
import com.moon.worker.utils.SharedPreferencesUtil
import com.moon.worker.utils.ValidateUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @Des：
 * @author: moon
 * @date: 3/13/23
 */
class PwdLoginViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(AccountUIData())

    val uiState : StateFlow<AccountUIData> = _uiState

    fun emitPhoneNumber(phoneNumber : String) {
        viewModelScope.launch {
            _uiState.emit(uiState.value.copy(phoneNumber = phoneNumber))
        }
    }

    fun emitPwd(pwd : String) {
        viewModelScope.launch {
            _uiState.emit(uiState.value.copy(passWord = pwd))
        }
    }

    fun enabledButton() : Boolean{
       return ValidateUtil.isMobile(uiState.value.phoneNumber)
               && ValidateUtil.isPwd(uiState.value.passWord)
    }

    fun clearMobile() {
        emitPhoneNumber("")
    }

    fun clearPassword() {
        emitPwd("")
    }

    fun pwdLogin(loginResult:(Boolean) -> Unit) {
        viewModelScope.launch {
            val phone = uiState.value.phoneNumber
            val pwd = uiState.value.passWord
            val result = RequestManager.login("+1$phone",pwd)
            println("testLogin : ${result.toString()}")
            val token = result.data?.accessToken

            if (token?.isNotEmpty() == true){
                AccountHelper.token = token
                SharedPreferencesUtil.getSPInstance(LinqApplication.application).putSP("token",token)
                loginResult(true)
            }
        }

    }

}