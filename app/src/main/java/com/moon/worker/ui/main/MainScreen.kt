package com.moon.worker.ui.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moon.worker.R
import com.moon.worker.ui.message.MessageScreen
import com.moon.worker.ui.site.list.SiteListScreen
import com.moon.worker.ui.theme.tabNormalActiveColor
import com.moon.worker.ui.theme.tabNormalColor

data class BottomItem(val label: String, val selectItemRes: Int, val unSelectItemRes: Int ,val route: String = Screen.Message.route)

val _bottomItems = listOf<BottomItem>(
    BottomItem("Message" , R.drawable.tab_contact_selected , R.drawable.tab_contact_unselected , route = Screen.Message.route),
    BottomItem("Site" , R.drawable.tab_jobsite_select , R.drawable.tab_jobsite_unselect , route = Screen.Site.route),
    BottomItem("Notification" , R.drawable.tab_work_selected , R.drawable.tab_work_unselected , route = Screen.Notification.route),
    BottomItem("Me" , R.drawable.tab_profile_selected , R.drawable.tab_profile_unselected , route = Screen.Profile.route),
)

/**
 * @Des：主页面容器
 * @author: moon
 * @date: 3/9/23
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    // 脚手架
    var bottomSelectedState by remember { mutableStateOf(0) }
    val navControllers = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBarWidget(
                bottomSelectedState ,
                _bottomItems){ position , route ->

                Log.i("moon" ,"$position , $route")
                bottomSelectedState = position
                
                navControllers.navigate(route){
                    popUpTo = navControllers.graph.getStartDestination()
                    launchSingleTop = true
                }

            }
        },
        content = {
            NavHost(navController = navControllers, startDestination = Screen.Message.route){
                composable(Screen.Message.route){
                    MessageScreen(content = "1")
                }

                composable(Screen.Site.route){
                    SiteListScreen()
                }

                composable(Screen.Notification.route){
                    MessageScreen(content = "3")
                }

                composable(Screen.Profile.route){
                    MessageScreen(content = "4")
                }
            }
        }

    )

}



@Composable
fun BottomBarWidget(
    selectedPosition: Int,
    bottomItems: List<BottomItem>,
    onItemSelected: (position: Int , route : String) -> Unit
) {
    BottomNavigation(backgroundColor = Color.White) {
        bottomItems.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = selectedPosition == index,
                onClick = { onItemSelected.invoke(index , item.route) },
                icon = {
                    var iconRes = item.unSelectItemRes
                    var iconColor = tabNormalColor
                    if (selectedPosition == index) {
                        iconRes = item.selectItemRes
                        iconColor = tabNormalActiveColor
                    }
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(bottom = 4.dp),
                        tint = iconColor,
                    )
                },
                label = {
                    val labelStyle = if (selectedPosition == index) {
                        TextStyle(
                            fontWeight = FontWeight.Medium,
                            color = tabNormalActiveColor,
                            fontSize = 11.sp
                        )
                    } else {
                        TextStyle(
                            fontWeight = FontWeight.Normal,
                            color = tabNormalColor,
                            fontSize = 11.sp
                        )
                    }
                    Text(
                        text = bottomItems[index].label,
                        style = labelStyle,
                    )
                },
            )
        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Message : Screen("tab_message", R.string.tab_message)
    object Site : Screen("tab_site", R.string.tab_site)
    object Notification : Screen("tab_notification", R.string.tab_notification)
    object Profile : Screen("tab_me", R.string.tab_me)

}