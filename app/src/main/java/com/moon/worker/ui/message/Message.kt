package com.moon.worker.ui.message

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

/**
 * @Des：
 * @author: moon
 * @date: 4/10/23
 */
@Composable
fun MessageScreen(content : String) {
    Log.i("moon" , content)
    Text(text = content)
}
