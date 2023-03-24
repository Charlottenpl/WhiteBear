package com.sky.whitebear

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
            chat.msg = "分隔线是将列表和布局中的内容分组的内容分组的内容分组的细线。$i"
            chat.msgStatus = chat_text
            chatList.add(chat)
        }
    }
}


@Preview
@Composable
fun chat(){
    val chat = ChatDate()
    chat.id = 2
    chat.msg = "分隔线是将列表和布局中的内容分组列表和布局中的内容分组列表和布局中的内容分组的细线列表和布局中的内容分组的细线。"
    chat.msgStatus = chat_text
    model.chatList.add(chat)

    Column(Modifier.fillMaxSize()) {
        chatView(Modifier.weight(2f))
    }
}


@Composable
fun chatView( modifier: Modifier = Modifier){

    //占位布局
    ChatSpecer()

    //顶部栏
    ChatUserInfo()

    //聊天列表
    ChatList()

}


/**
 * 占位布局
 */
@Composable
fun ChatSpecer(){
    Spacer(modifier = Modifier
        .height(model.status_bar_heigh)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.secondaryContainer)) //占位
}


/**
 * 顶部拦
 */
@Composable
fun ChatUserInfo(){

    Row(modifier = Modifier
        .height(60.dp)
        .padding(0.dp, 0.dp, 0.dp, 0.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.secondaryContainer), horizontalArrangement = Arrangement.SpaceBetween) {

        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            Column(
                Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .height(60.dp)
                    .width(60.dp), verticalArrangement = Arrangement.Center) {
                //从网络加载图片就只能使用第三方库了
                AsyncImage(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                    model = "https://images.cnblogs.com/cnblogs_com/charlottepl/1676587/o_200321060715%E5%93%A6%E5%90%BC.png",
                    contentDescription = "user_img"
                )
            }

            //用户名称等信息
            Column() {
                Text(text = "Charlotte", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSecondaryContainer, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = "☁️ 天气晴朗，阳光明媚", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.8f), maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
        ChatDropMenu()
    }
}


/**
 * 聊天信息列表
 */
@Composable
fun ChatList(modifier: Modifier = Modifier){
    var chatList = model.chatList
    var index = 0;
    var state = rememberScrollState()

    Column(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        LazyColumn (modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(0.dp,0.dp)){
            itemsIndexed(chatList, key = { index, item ->
                // 可以使用 item 的某个属性或哈希码作为键
                index
            }) { index, item ->
                // view
                Row(
                    Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())) {
                    Row(
                        Modifier.fillMaxWidth()) {
                        Box(
                            Modifier
                                .fillParentMaxWidth()
                                .clickable {
                                    //TODO 打开详情页...

                                }) {
                            ChatItem(chat = item)
                        }

                        Box( modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .height(60.dp)
                            .width(80.dp), contentAlignment = Alignment.Center) {
                            Text(text = "已读", modifier = Modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.onSecondaryContainer)
                        }

                        Box( modifier = Modifier
                            .background(MaterialTheme.colorScheme.error)
                            .height(60.dp)
                            .width(80.dp), contentAlignment = Alignment.Center) {
                            Text(text = "删除", modifier = Modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.onError)
                        }
                    }
                }
            }
        }
    }
}


/**
 * chat顶部菜单栏
 */
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


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatItem(chat: ChatDate){

    Column(Modifier.padding(10.dp,0.dp, 20.dp,0.dp)) {
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
                Text(text = "发送者${chat.id}", fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = chat.msg, fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground.copy(0.6f), maxLines = 1, overflow = TextOverflow.Ellipsis) //如果文字溢出则显示省略号
            }
//            Button(modifier = Modifier
//                .height(25.dp)
//                .padding(0.dp)
//                .width(0.dp)
//                .weight(1f),
//                contentPadding = PaddingValues(5.dp,2.dp),
//                onClick = {
//                    model.delChatDateLast()
//                    println("剩余数量"+model.chatList.size)
//                },) {
//                Text(text = "删除", fontSize = 10.sp)
//            }
        }
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.primary.copy(0.1f), modifier = Modifier)
    }
}