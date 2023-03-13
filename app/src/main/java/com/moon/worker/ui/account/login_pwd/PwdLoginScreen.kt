package com.moon.worker.ui.account.login_pwd

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
import com.moon.worker.ui.common.CommonBlueButton

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
                .width(55.dp),
            painter = painterResource(id = R.drawable.app_icon), contentDescription = "")

        Text(
            modifier = Modifier.padding(start = 30.dp, top = 16.dp),
            style = TextStyle(fontSize = 16.sp , color = Color.Black),
            text = "Linq Pros")

        TextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp , vertical = 34.dp)
            ,value = uiData.value.phoneNumber, onValueChange = {
                viewModel.emitPhoneNumber(it)
        })

        TextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp , vertical = 34.dp)
            ,value = uiData.value.passWord, onValueChange = {
                viewModel.emitPwd(it)
            })

        CommonBlueButton(
            title = "Sign In",
            enabledButton = viewModel.enabledButton(),
            click = {
                context.let {
                    it.startActivity(Intent(it, PwdLoginActivity::class.java))
                }
            }
        )

    }
}