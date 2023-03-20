package com.sky.whitebear

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsHeight
import com.sky.whitebear.date.bean.ChatDate
import com.sky.whitebear.date.chat_text
import com.sky.whitebear.date.model

object ChatView{

    //初始化数据
    fun initDate(){
        val chatList = model.chatList
        chatList.clear()
        //模拟初始化数据过程，真实过程请从数据库获取
        val i = chatList.lastIndex;
        for (i in i + 1..i+10){
            val chat = ChatDate()
            chat.id = i
            chat.msg = "分隔线是将列表和布局中的内容分组的细线。$i"
            chat.msgStatus = chat_text
            chatList.add(chat)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun chat(){
    val chat = ChatDate()
    chat.id = 2
    chat.msg = "分隔线是将列表和布局中的内容分组列表和布局中的内容分组列表和布局中的内容分组的细线。"
    chat.msgStatus = chat_text
    model.chatList.add(chat)

    Column(Modifier.fillMaxSize()) {
        chatView(Modifier.weight(2f))
    }
}


@Composable
fun chatView( modifier: Modifier = Modifier){
    var chatList = model.chatList

    Spacer(modifier = Modifier
        .height(model.status_bar_heigh)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary.copy(0.3f))) //占位
    Row(modifier = Modifier
        .height(60.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary.copy(0.3f)), horizontalArrangement = Arrangement.SpaceBetween) {

//        IconButton(modifier = Modifier.height(60.dp),onClick = { /*TODO*/ },) {
//            Icon(
//                imageVector = Icons.Rounded.Search,
//                modifier = Modifier,
//                contentDescription = "个人信息",
//                tint = MaterialTheme.colorScheme.primary.copy(0.8f)
//            )
//        }
        ChatDropMenu()
    }
    Column(modifier = modifier.fillMaxWidth()) {
        LazyColumn (modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(0.dp,5.dp)){
            itemsIndexed(chatList, key = { index, item ->
                // 可以使用 item 的某个属性或哈希码作为键
                index
            }) { index, item ->
                // view
                ChatItem(chat = item)

            }
        }
    }

}


@Composable
fun ChatDropMenu(){
    var expander by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .height(60.dp)
        .wrapContentSize(Alignment.TopStart)) {
        IconButton(modifier = Modifier.height(60.dp), onClick = { expander = true }) {
            Icon(Icons.Rounded.Menu, contentDescription = "Localized description")
        }
        DropdownMenu(expanded = expander, onDismissRequest = { expander = false }) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = { /* Handle edit! */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Edit,
                        contentDescription = null
                    )
                })
            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = { /* Handle settings! */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Settings,
                        contentDescription = null
                    )
                })
            Divider()
            DropdownMenuItem(
                text = { Text("Send Feedback") },
                onClick = { /* Handle send feedback! */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Email,
                        contentDescription = null
                    )
                },
                trailingIcon = { Text("F11", textAlign = TextAlign.Center) })
        }
    }
}


@Composable
fun ChatUserInfo(){
    Image(bitmap = , contentDescription = "userInfo")
}


@Composable
fun ChatItem(chat: ChatDate){
//    var msg = chat.msg
//    var show_msg = if (msg.length > 10){
//        msg.substring(0,10) + "..."
//    }else{
//        msg
//    }
    Column(Modifier.padding(10.dp,0.dp)) {
        Row(modifier = Modifier
            .height(60.dp)
            .padding(0.dp, 3.dp)
            .fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier
                .width(0.dp)
                .weight(1f)
                .fillMaxHeight()
                .padding(0.dp, 3.dp)){
                Icon(
                    imageVector = Icons.Rounded.AccountBox,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "点击按钮",
                    tint = MaterialTheme.colorScheme.primary.copy(0.8f)
                )
            }

            Column(modifier = Modifier
                .width(0.dp)
                .weight(5f)) {
                Text(text = "发送者${chat.id}")
                Text(text = chat.msg, maxLines = 1, overflow = TextOverflow.Ellipsis) //如果文字溢出则显示省略号
            }
            Button(modifier = Modifier
                .height(25.dp)
                .padding(0.dp)
                .width(0.dp)
                .weight(1f),
                contentPadding = PaddingValues(5.dp,2.dp),
                onClick = {
                    model.delChatDateLast()
                    println("剩余数量"+model.chatList.size)
                    model.status_bar_heigh = 2.dp
                },) {
                Text(text = "删除", fontSize = 10.sp)
            }
        }
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.primary.copy(0.1f), modifier = Modifier)
    }
}