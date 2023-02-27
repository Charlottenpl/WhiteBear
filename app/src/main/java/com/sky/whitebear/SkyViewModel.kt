package com.sky.whitebear

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 *
 * @author: charlotte
 * @date: 2023/2/26
 */

class SkyViewModel : ViewModel() {
    var selectId by mutableStateOf(1)        //当前选中Nav id
    var navNames by mutableStateOf(listOf("首页","设置"))  //Nav tiitle
}