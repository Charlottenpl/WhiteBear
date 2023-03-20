package com.sky.whitebear


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sky.whitebear.date.*
import com.sky.whitebear.service.MusicService
import com.sky.whitebear.ui.theme.WhiteBearTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(SkyViewModel::class.java) //初始化全局model

        WindowCompat.setDecorFitsSystemWindows(window, false)//false 则内容进入状态栏
        setContent {
                rememberSystemUiController().setStatusBarColor(
                    Color.Transparent, darkIcons = setting_theme_id != theme_dark)
                    Column() {
                        getStatusBarHeigh()
                        skyView()
                    }
        }

        initDate()
        // 启动 MusicService
        var musicServiceIntent = Intent(this, MusicService::class.java)
        startService(musicServiceIntent)
    }

    fun initDate(){
        ChatView.initDate()
    }
}


@Composable
fun getStatusBarHeigh(){
    //状态栏高度
    val statusBarHeightDp = LocalDensity.current.run {
        WindowInsets.statusBars.getTop(this).toDp()
    }
    model.status_bar_heigh = statusBarHeightDp
    println("状态栏高度$statusBarHeightDp")
}


@Preview(showBackground = true)
@Composable
fun skyView() {
    WhiteBearTheme(type = setting_theme_id) {
        Column(Modifier.fillMaxSize()) {
            var viewModel: SkyViewModel = viewModel()


            mainView(
                Modifier
                    .weight(1f)
                    .height(0.dp)
                    .fillMaxWidth())
            Navs(selectId = viewModel.selectId)
        }
    }
}


@Composable
fun mainView(modifier: Modifier = Modifier) {
    println("mainView 重构")
    var viewModel: SkyViewModel = viewModel()
    when (viewModel.selectId) {
        nav_home -> {
            homeView(modifier)
        }
        nav_music -> {
            musicView(modifier)
        }
        nav_chat -> {
            chatView(modifier)
        }
        nav_setting -> {
            settingView(modifier)
        }

    }

}

