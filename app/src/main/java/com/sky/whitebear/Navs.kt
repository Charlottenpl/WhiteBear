package com.sky.whitebear

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sky.whitebear.date.nav_chat
import com.sky.whitebear.date.nav_home
import com.sky.whitebear.date.nav_music
import com.sky.whitebear.date.nav_setting

/**
 *
 * @author: charlotte
 * @date: 2023/2/26
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navs(selectId: Int) {

    var viewModel: SkyViewModel = viewModel()
    val menuData = listOf(
        BtnDate("首页", Icons.Rounded.Home, nav_home),
        BtnDate("聊天", Icons.Rounded.Person, nav_chat),
        BtnDate("音乐", Icons.Rounded.Menu, nav_music),
        BtnDate("设置", Icons.Rounded.Settings, nav_setting)
    )


    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        menuData.forEachIndexed { index, s ->
            NavigationBarItem(
                selected = s.id == viewModel.selectId,
                onClick = {
                    viewModel.selectId = s.id
                },
                icon = {
                    Icon(
                        imageVector = s.icon,
                        contentDescription = "点击按钮",
                        tint = if (viewModel.selectId == index) MaterialTheme.colorScheme.primary else Color.Black
                    )
                },
                label = {
                    Text(
                        text = (s.name)
                    )
                },
            )
        }

    }
//    Scaffold(modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//
//    }
//    ) { innerPadding ->
//
//        //根据按钮选择展示的view
//        println(innerPadding)
//        Box(Modifier.fillMaxSize()) {
//            if (viewModel.selectId == nav_home) {
//                mainView(modifier = Modifier)
//            } else if (viewModel.selectId == nav_chat) {
//                chatView(modifier = Modifier)
//            } else if (viewModel.selectId == nav_music) {
//                musicView(modifier = Modifier)
//            } else if (viewModel.selectId == nav_setting) {
//                settingView(modifier = Modifier)
//            }
//        }
//
//
//    }
}


@Preview
@Composable
fun show1(){
    Navs(selectId = 2)
}

data class BtnDate(var name: String, var icon: ImageVector, var id: Int){

}

fun onClick() {

}

@Composable
fun NavItem(name: String, id: Int, modifier: Modifier) {
    var viewModel: SkyViewModel = viewModel()
    Column(modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = name,
            Modifier.size(30.dp),
            tint = if (viewModel.selectId == id) MaterialTheme.colorScheme.primary else Color.Black
        )
        Text(
            text = name,
            fontSize = 12.sp,
            color = if (viewModel.selectId == id) MaterialTheme.colorScheme.primary else Color.Black
        )
    }
}
