package com.sky.whitebear

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview(showBackground = true)
@Composable
fun show(){
    Column(Modifier.fillMaxSize()) {
        homeView(Modifier.weight(2f))
    }
}


@Composable
fun homeView( modifier: Modifier = Modifier){
    var viewModel: SkyViewModel = viewModel()
    var items = listOf<String>("a","n","c","d","a","n","c","d","a","n","c","d","a","n","c","d","a","n","c","d","a","n","c","d","a","n","c","d","a","n","c","1","2")

    LazyColumn (modifier = modifier.fillMaxSize()){
        itemsIndexed(items, key = { index, item ->
            // 可以使用 item 的某个属性或哈希码作为键
            index
        }) { index, item ->
            // view
            Text(text = item)
        }

    }



}