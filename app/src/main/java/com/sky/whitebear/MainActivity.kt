package com.sky.whitebear

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
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
    WhiteBearTheme(type = 1) {
        Column(Modifier.fillMaxSize()) {
            var viewModel: SkyViewModel = viewModel()
            mainView(Modifier.weight(2f))
            Navs(selectId = viewModel.selectId)
        }
    }
}

