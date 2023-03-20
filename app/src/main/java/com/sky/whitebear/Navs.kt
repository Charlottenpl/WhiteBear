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
                        tint = if (viewModel.selectId == s.id) MaterialTheme.colorScheme.primary else Color.Black
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
