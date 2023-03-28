package com.moon.worker.ui.account.login_pwd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.moon.worker.ui.account.login_verify_code.VerifyCodeLoginScreen
import com.moon.worker.ui.theme.MyworkerTheme

/**
 * @Des：登录-验证码
 * @author: moon
 * @date: 3/10/23
 */
class VerifyCodeLoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyworkerTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = Color.White
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    VerifyCodeLoginScreen()
                }
            }
        }
    }
}