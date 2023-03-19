package com.sky.whitebear


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sky.whitebear.date.*
import com.sky.whitebear.service.MusicService
import com.sky.whitebear.ui.theme.WhiteBearTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            skyView()
        }
        // 启动 MusicService
        var musicServiceIntent = Intent(this, MusicService::class.java)
        startService(musicServiceIntent)
    }
}


@Preview(showBackground = true)
@Composable
fun skyView() {
    WhiteBearTheme(type = setting_theme_id) {
        Column(Modifier.fillMaxSize()) {
            var viewModel: SkyViewModel = viewModel()


            mainView(Modifier.weight(1f).height(0.dp).fillMaxWidth())
            Navs(selectId = viewModel.selectId)
        }
    }
}


@Composable
fun mainView(modifier: Modifier = Modifier) {
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

