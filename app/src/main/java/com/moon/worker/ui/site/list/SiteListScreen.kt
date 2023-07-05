package com.moon.worker.ui.site.list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moon.worker.R
import com.moon.worker.ui.theme.hintTextColor
import com.moon.worker.ui.theme.majorBlue
import com.moon.worker.utils.showToast

/**
 * @Des：site list 页面（主页tab之一）
 * @author: moon
 * @date: 6/28/23
 */
@Composable
fun SiteListScreen(viewModel: SiteListViewModel = androidx.lifecycle.viewmodel.compose.viewModel())  {

    val datas = viewModel.testList

    val listState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxHeight()) {
        // top title
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)) {
            Text(text = "All projects" ,
                style = TextStyle(fontSize = 14.sp , color = hintTextColor),
                modifier = Modifier.padding(10.dp))
            Spacer(modifier = Modifier.weight(1.0f))
            Image(
                modifier = Modifier
                    .width(12.dp)
                    .height(12.dp)
                    .clickable {
                        toCompleteSiteListPage()
                    },
                painter = painterResource(id = R.drawable.notification_title_icon), contentDescription = "")

            Text(text = "Complete Sites" , modifier = Modifier
                .padding(10.dp)
                .clickable {
                    toCompleteSiteListPage()
                },
                style = TextStyle(fontSize = 14.sp , color = majorBlue),)
        }

        // site list
        LazyColumn(state = listState,
            contentPadding = PaddingValues(all = 16.dp)
            ){
            items(datas) { site ->
                Text(text = site.title)
            }
        }
        
    }
}


fun toCompleteSiteListPage() {
    showToast("to complete site list page")
}