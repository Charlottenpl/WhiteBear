package com.sky.whitebear

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.sky.whitebear.date.bean.ChatDate
import com.sky.whitebear.date.bean.User
import com.sky.whitebear.date.nav_chat
import com.sky.whitebear.date.theme_spring

/**
 *
 * @author: charlotte
 * @date: 2023/2/26
 */

class SkyViewModel : ViewModel() {
    /**
     * Basic Date
     */
    var selectId by mutableStateOf(nav_chat)        //当前选中Nav id

    /**
     * User Info
     */
    var user by mutableStateOf(User()) //当前用户信息

    /**
     * System Setting
     */
    var status_bar_heigh by mutableStateOf(0.dp) //系统栏高度
    var setting_is_system_dark by mutableStateOf(true) //是否跟随系统夜间模式
    var setting_theme_id by mutableStateOf(theme_spring) //当前主题

    /**
     * Chat Model
     */
    var chatList:MutableList<ChatDate> = mutableStateListOf()
    fun delChatDateLast(){
        chatList.removeLast()
    }

}
