package com.sky.whitebear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sky.whitebear.ui.theme.WhiteBearTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                var viewModel: SkyViewModel = viewModel()
                mainView()
                Navs(selectId = viewModel.selectId)
                
            }

        }
    }
}





@Preview
@Composable
fun mainView(){
    var modifier = Modifier.weight(1f)
    Column(modifier) {
        Text(text = "hhhh")
    }
}


@Preview(showBackground = true)
@Composable
fun skyView() {
    WhiteBearTheme(true) {
        Column {
            var viewModel: SkyViewModel = viewModel()
            mainView()
            Navs(selectId = viewModel.selectId)

        }
    }
}

