package com.moon.worker.ui.account.login_pwd

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moon.worker.ui.account.AccountUIData
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
       return uiState.value.phoneNumber.isNotEmpty() && uiState.value.passWord.isNotEmpty()
    }


}