package com.moon.worker.ui.account.login_pwd

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moon.worker.R
import com.moon.worker.ui.account.widget.MobileTextFieldComponent
import com.moon.worker.ui.account.widget.PasswordTextFieldComponent
import com.moon.worker.ui.common.CommonBlueButton
import com.moon.worker.ui.main.MainActivity

/**
 * @Des：
 * @author: moon
 * @date: 3/13/23
 */

@Composable
fun PwdLoginScreen(
    viewModel: PwdLoginViewModel = viewModel()
){

    val uiData = viewModel.uiState.collectAsState()

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .padding(start = 10.dp, top = 60.dp)
                .width(75.dp),
            painter = painterResource(id = R.drawable.app_icon), contentDescription = "")

        Text(
            modifier = Modifier.padding(start = 30.dp, top = 16.dp),
            style = TextStyle(fontSize = 16.sp , color = Color.Black),
            text = "Linq Pros")


        MobileTextFieldComponent(
            mobile = uiData.value.phoneNumber,
            mobileChanged = {
                viewModel.emitPhoneNumber(it)
            },
            mobileClear = {
                viewModel.clearMobile()
            }
        )

        PasswordTextFieldComponent(
            password = uiData.value.passWord,
            passwordChanged = {
                viewModel.emitPwd(it)
            },
            passwordClear = {
                viewModel.clearPassword()
            },
        )

        CommonBlueButton(
            title = "Sign In",
            enabledButton = viewModel.enabledButton(),
            click = {
                viewModel.pwdLogin(loginResult = {
                    context.let {
                        it.startActivity(Intent(it, MainActivity::class.java))
                        (context as Activity).finish()
                    }
                })

            }
        )

    }
}