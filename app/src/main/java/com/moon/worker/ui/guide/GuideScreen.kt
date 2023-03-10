package com.moon.worker.ui.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.moon.worker.ui.theme.majorBlue
import com.moon.worker.ui.theme.majorTextColor
import com.moon.worker.ui.theme.minorTextColor

/**
 * @Des：
 * @author: moon
 * @date: 3/10/23
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GuideScreen(
    viewModel: GuideViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val items = viewModel.guideItems

    val pagerState = rememberPagerState(
        initialPage = 0,
    )

    Column(modifier = Modifier) {

        Spacer(modifier = Modifier.height(60.dp))

        HorizontalPager(
            state = pagerState,
            count = items.size
        ) { index ->
            GuideCardItem(items[index])
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 23.dp),
            activeColor = majorBlue,
            inactiveColor = Color(0xffd5ecfd)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = majorBlue,),
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 27.dp)
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .clip(shape = RoundedCornerShape(8.dp))
            ,
            onClick = {

            }
        ) {
            androidx.compose.material.Text(
                color = Color.White,
                text = "Sign In",
                fontSize = 16.sp
            )
        }


    }
}

@Composable
fun GuideCardItem(item : GuideUiState){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {

        Image(
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            painter = painterResource(id = item.icon), contentDescription = "")
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            color = majorTextColor,
            text = item.title,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.padding(horizontal = 70.dp),
            color = minorTextColor,
            text = item.description,
            fontSize = 14.sp
        )


    }
}