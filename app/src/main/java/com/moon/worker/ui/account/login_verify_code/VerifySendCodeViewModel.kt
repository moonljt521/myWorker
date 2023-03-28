package com.moon.worker.ui.account.login_verify_code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moon.worker.request.AccountHelper
import com.moon.worker.request.RequestManager
import com.moon.worker.request.model.SendCodeType
import com.moon.worker.ui.account.AccountUIData
import com.moon.worker.utils.ValidateUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @Des：
 * @author: moon
 * @date: 3/28/23
 */
class VerifySendCodeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AccountUIData())

    val uiState : StateFlow<AccountUIData> = _uiState

    fun emitPhoneNumber(phoneNumber : String) {
        viewModelScope.launch {
            _uiState.emit(uiState.value.copy(phoneNumber = phoneNumber))
        }
    }

    fun enabledButton() : Boolean{
        return ValidateUtil.isMobile(uiState.value.phoneNumber)
    }

    fun clearMobile() {
        emitPhoneNumber("")
    }

    fun sendCode(sendCodeResult:(Boolean) -> Unit) {
        viewModelScope.launch {
            val phone = uiState.value.phoneNumber
            val result = RequestManager.sendCode("+1$phone" , SendCodeType.login)
            if (result.code == 0){
                sendCodeResult(true)
            }
        }

    }


}