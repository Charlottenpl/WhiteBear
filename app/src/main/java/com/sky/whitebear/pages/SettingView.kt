package com.sky.whitebear

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview(showBackground = true)
@Composable
fun settingshow(){
    Column(Modifier.fillMaxSize()) {
        settingView(Modifier.weight(2f))
    }
}


@Composable
fun settingView( modifier: Modifier = Modifier){
    var viewModel: SkyViewModel = viewModel()
    Column(modifier) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Setting")
            Text(text = "acc")
            Text(text = "acc")
        }
        
        Box(Modifier.fillMaxWidth()) {
            Text(text = "bcc")
        }
    }
}