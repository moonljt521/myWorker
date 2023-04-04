package com.moon.worker.ui.account.login_verify_code_fill

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moon.worker.R
import com.moon.worker.ui.account.login_pwd.PwdLoginActivity
import com.moon.worker.ui.account.widget.MobileTextFieldComponent
import com.moon.worker.ui.common.CommonBlueButton
import com.moon.worker.ui.main.MainActivity
import com.moon.worker.ui.theme.hintTextColor
import com.moon.worker.ui.theme.minorTextColor

/**
 * @Des：验证码登录
 * @author: moon
 * @date: 3/13/23
 */

@Composable
fun VerifyCodeLoginFillScreen(
    phone: String,
    viewModel: VerifyCodeFillViewModel = viewModel()
){

    val uiData = viewModel.uiState.collectAsState()

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .padding(start = 10.dp, top = 60.dp)
                .width(75.dp)
                .height(48.dp),
            painter = painterResource(id = R.drawable.app_icon), contentDescription = "")

        Text(
            modifier = Modifier.padding(start = 30.dp, top = 16.dp, bottom = 30.dp),
            style = TextStyle(fontSize = 26.sp , color = Color.Black , fontWeight = FontWeight.Bold),
            text = "Enter the code")


        Text(
            modifier = Modifier
                .padding(start = 25.dp, top = 6.dp, bottom = 15.dp)
                .align(androidx.compose.ui.Alignment.Start),
            style = TextStyle(fontSize = 13.sp , color = hintTextColor),
            text = "Verification code has benn sent to 111119")

        Box(modifier = Modifier.align(Alignment.CenterHorizontally)){

            VerificationCodeField(digits = 6 , inputCallback = { code ->
                viewModel.emitCode(code)
            } , itemScope = { text,focused->
                SimpleVerificationCodeItem(text,focused)
            })
        }
        
        Text(
            modifier = Modifier
                .padding(start = 25.dp, top = 46.dp, bottom = 25.dp)
                .align(androidx.compose.ui.Alignment.Start),
            style = TextStyle(fontSize = 13.sp , color = hintTextColor),
            text = "Verification code may be delayed, please be patient")


        CommonBlueButton(
            title = "Sign In",
            enabledButton = viewModel.enabledButton(uiData.value.code),
            click = {
                viewModel.verifyCodeLogin(phone) {
                    context.let {
                        it.startActivity(Intent(it, MainActivity::class.java))
                        (context as Activity).finish()
                    }
                }
            }
        )

    }
}