package com.moon.worker.ui.account.login_verify_code

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.moon.worker.ui.account.login_verify_code_fill.VerifyCodeLoginFillActivity
import com.moon.worker.ui.account.widget.MobileTextFieldComponent
import com.moon.worker.ui.common.CommonBlueButton
import com.moon.worker.ui.theme.hintTextColor
import com.moon.worker.ui.theme.minorTextColor

/**
 * @Des：验证码登录
 * @author: moon
 * @date: 3/13/23
 */

@Composable
fun VerifyCodeLoginScreen(
    viewModel: VerifySendCodeViewModel = viewModel()
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
        
        Text(
            modifier = Modifier.padding(start = 25.dp, top = 6.dp, bottom = 15.dp)
                .align(androidx.compose.ui.Alignment.CenterHorizontally),
            style = TextStyle(fontSize = 13.sp , color = hintTextColor),
            text = "Non-registered phone number will generate a new Linq Pros account")


        CommonBlueButton(
            title = "Send Code",
            enabledButton = viewModel.enabledButton(),
            click = {
                viewModel.sendCode(sendCodeResult = {
                    context.let {
                        Toast.makeText(context , "The verify code has benn sent" , Toast.LENGTH_LONG).show()
                        it.startActivity(Intent(it, VerifyCodeLoginFillActivity::class.java)
                            .putExtra("phone", viewModel.uiState.value.phoneNumber))
                    }
                })
            }
        )

        Text(text = "Password Sign In" ,
            modifier = Modifier.padding(top = 16.dp)
                .align(androidx.compose.ui.Alignment.CenterHorizontally)
                .clickable {
                    context.let {
                        it.startActivity(Intent(it, PwdLoginActivity::class.java))
                    }
                }.padding(all = 10.dp),
            style = TextStyle(fontSize = 15.sp , color = minorTextColor),
            )

    }
}