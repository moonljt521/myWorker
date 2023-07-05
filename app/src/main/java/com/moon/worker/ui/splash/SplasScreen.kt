package com.moon.worker.ui.splash

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.moon.worker.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moon.worker.ui.guide.GuideActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.lifecycle.viewModelScope
import com.moon.worker.ui.main.MainActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

/**
 * @Des：
 * @author: moon
 * @date: 3/10/23
 */

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Splash(
    splashViewModel: SplashViewModel = viewModel()
) {

    val splashUiState by splashViewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(60.dp)
                .height(60.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            painter = painterResource(id = R.drawable.app_icon), contentDescription = "")

        Text(text = "Linq Pros", fontSize = 25.sp, color = Color.White)
    }

    val context = LocalContext.current

    LaunchedEffect(splashUiState.turnToGuidePage){

        if (splashUiState.turnToGuidePage){
            withContext(Dispatchers.Main){
                context.startActivity(Intent(context,
                    MainActivity::class.java))
                (context as Activity).finish()
            }
        }
    }

}

