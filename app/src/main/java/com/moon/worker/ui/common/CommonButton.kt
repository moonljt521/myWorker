package com.moon.worker.ui.common

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moon.worker.ui.account.login_pwd.PwdLoginActivity
import com.moon.worker.ui.theme.majorBlue

/**
 * @Des：
 * @author: moon
 * @date: 3/13/23
 */

@Composable
fun CommonBlueButton(
    title : String,
    enabledButton: Boolean = true,
    click:() -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = (if (enabledButton) majorBlue else Color.Gray)),
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .padding(vertical = 25.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .padding(vertical = 10.dp)
        ,
        onClick = {
            if (enabledButton) click()
        }
    ) {
        Text(
            color = Color.White,
            text = title,
            fontSize = 16.sp
        )
    }
}