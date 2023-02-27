package com.sky.whitebear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sky.whitebear.ui.theme.WhiteBearTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            skyView()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun skyView() {
    WhiteBearTheme(true) {
        Column(Modifier.fillMaxSize()) {
            var viewModel: SkyViewModel = viewModel()
            mainView(Modifier.weight(2f))
            Navs(selectId = viewModel.selectId)
        }
    }
}

