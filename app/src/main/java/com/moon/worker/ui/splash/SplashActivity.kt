package com.moon.worker.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.moon.worker.ui.theme.MyworkerTheme
import com.moon.worker.ui.theme.majorBlue

/**
 * @Des：
 * @author: moon
 * @date: 3/10/23
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyworkerTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = majorBlue
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = majorBlue
                ) {
                    Splash()
                }
            }
        }
    }

}