package com.moon.worker.ui.splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moon.worker.ui.guide.GuideActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @Des：
 * @author: moon
 * @date: 3/10/23
 */
class SplashViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState

    init {
        delayToMainPage()
    }

    fun delayToMainPage() {
        viewModelScope.launch {
            delay(2000)
            _uiState.value = SplashUiState(turnToGuidePage = true)
        }
    }
}