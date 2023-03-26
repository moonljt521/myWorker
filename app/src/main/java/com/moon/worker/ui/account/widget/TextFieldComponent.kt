package com.moon.worker.ui.account.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moon.worker.R

/**
 * @Des：
 * @author: moon
 * @date: 3/13/23
 */

// 手机号输入框，带国旗，国际码
@Composable
fun MobileTextFieldComponent(
    mobile : String,
    mobileChanged : (String) -> Unit,
    mobileClear : () -> Unit,
    isPassword: Boolean = false,
){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        singleLine = true,

        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        ),
        value = mobile, onValueChange = {
            if(it.length <= 10){
                val txt = it.replace(Regex("[^\\d]"), "")
                mobileChanged(txt)
            }
        },
        leadingIcon = {
            Row {
                Image(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 0.dp)
                        .width(45.dp),
                    painter = painterResource(id = R.drawable.login_usa_icon), contentDescription = "")

                5.dp
                Text(text = "(+1)")
            }
        },
        trailingIcon = {
            if (mobile.isNotEmpty()) Icon(
                imageVector = Icons.Outlined.Close, contentDescription = null,
            modifier = Modifier.clickable {
                mobileClear()
            })
            else 0.dp
        },

        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White
        ),
        placeholder = {
            Text(text = "Enter the phone number")
        } ,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) ,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}


// 密码输入框
@Composable
fun PasswordTextFieldComponent(
    password : String,
    passwordChanged : (String) -> Unit,
    passwordClear : () -> Unit,
){

    var showPassword by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        singleLine = true,

        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        ),
        value = password, onValueChange = {
            if(it.length <= 20){
                passwordChanged(it)
            }
        },

        trailingIcon = {
            if (password.isNotEmpty()) Row(
                modifier = Modifier.padding(end = 10.dp)
            ) {

                Icon(
                    imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff, contentDescription = null,
                    modifier = Modifier.clickable {
                        showPassword = !showPassword
                    })

                Icon(
                    imageVector = Icons.Outlined.Close, contentDescription = null,
                    modifier = Modifier.clickable {
                        passwordClear()
                    })

            }
            else 0.dp
        },

        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White
        ),
        placeholder = {
            Text(text = "Enter the login password")
        } ,
        visualTransformation = if (!showPassword) PasswordVisualTransformation()
            else VisualTransformation.None
    )
}