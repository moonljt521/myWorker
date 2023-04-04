package com.moon.worker.ui.account.login_verify_code_fill

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @Des：
 * @author: moon
 * @date: 4/4/23
 */


/**
 * @param text 文本内容
 * @param focused 是否高亮当前输入框
 */
@Composable
fun SimpleVerificationCodeItem(text: String, focused: Boolean) {
    val borderColor = if (focused) Color.Gray else Color(0xeeeeeeee)

    Box(
        modifier = Modifier
            .border(1.dp, borderColor)
            .size(55.dp, 55.dp), contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }

}

/**
 * @param digits 验证码位数（框数量）
 * @param horizontalMargin 水平间距
 * @param inputCallback 输入回调
 */
@Composable
fun VerificationCodeField(
    digits: Int,
    horizontalMargin: Dp = 10.dp,
    inputCallback: (content: String) -> Unit = {},
    itemScope: @Composable (text: String, focused: Boolean) -> Unit
) {
    var content by remember { mutableStateOf("") }
    Box {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //绘制框
            repeat(digits) {
                if (it != 0) {
                    //添加间距
                    Spacer(modifier = Modifier.width(horizontalMargin))
                }
                //获取当前框的文本
                val text = content.getOrNull(it)?.toString() ?: ""
                //是否正在输入的框
                val focused = it == content.length
                //绘制文本
                itemScope(text, focused)
            }

        }
        BasicTextField(value = content, onValueChange = {
            content = it
            inputCallback(it)
        }, modifier = Modifier
            .drawWithContent { }//清楚绘制内容
            .matchParentSize())//填充至父布局大小
    }

}

