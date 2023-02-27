package com.sky.whitebear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview(showBackground = true)
@Composable
fun show(){
    Column(Modifier.fillMaxSize()) {
        mainView(Modifier.weight(2f))
    }
}


@Composable
fun mainView( modifier: Modifier){
    Column(modifier) {
        Row(Modifier.fillMaxWidth().background(Color.White), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "acc")
            Text(text = "acc")
            Text(text = "acc")
        }
        
        Box(Modifier.fillMaxWidth()) {
            Text(text = "bcc")
        }
    }
}