package com.sky.whitebear

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sky.whitebear.ui.theme.ThemeColor

/**
 *
 * @author: charlotte
 * @date: 2023/2/26
 */


@Composable
fun Navs(selectId: Int) {
    var viewModel: SkyViewModel = viewModel()
    Row() {
        NavItem(name = "首页", 1,
            Modifier.weight(1f).clickable {
                viewModel.selectId = 1
            })

        NavItem(name = "测试", 2,
            Modifier.weight(1f).clickable {
                viewModel.selectId = 2
            })

        NavItem(name = "资料", 3,
            Modifier.weight(1f).clickable {
                viewModel.selectId = 3
            })
    }

}

@Composable
fun NavItem(name: String, id: Int, modifier: Modifier) {
    var viewModel: SkyViewModel = viewModel()
    Column(modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = name,
            Modifier.size(30.dp),
            tint = if (viewModel.selectId == id) MaterialTheme.colors.primary else Color.Black
        )
        Text(
            text = name,
            fontSize = 12.sp,
            color = if (viewModel.selectId == id) MaterialTheme.colors.primary else Color.Black
        )
    }


}


fun changeThemeColor(color: Color) {
    ThemeColor = color
}
