package com.sky.whitebear.date.bean

import androidx.compose.foundation.ScrollState

/**
 * 消息类
 * @author: charlotte
 * @date: 2023/3/19
 */
class ChatItem {
    var id: Int = 0         //id
    var msg:String = ""     //显示信息
    var msgStatus: Int = 0  //消息状态 = 已读:未读
    var from: Int = 0       //好友id 后期改成User类型？
    var to: Int = 0         //当前user
    var status: Int = 0     //置顶？
    lateinit var swipeState: ScrollState        //列表项滑动状态
}