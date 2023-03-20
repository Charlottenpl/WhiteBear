package com.sky.whitebear

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.sky.whitebear.date.bean.ChatDate

/**
 *
 * @author: charlotte
 * @date: 2023/2/26
 */

class SkyViewModel : ViewModel() {
    var selectId by mutableStateOf(0)        //当前选中Nav id

    //sys setting
    var status_bar_heigh by mutableStateOf(0.dp) //系统栏高度

    //Chat
    var chatList:MutableList<ChatDate> = mutableStateListOf()

    fun addChatData(chatData: ChatDate) {
        chatList.add(chatData)
    }

    fun delChatDate(index:Int){
        chatList.removeAt(index)
    }

    fun delChatDateLast(){
        chatList.removeLast()
    }

}
