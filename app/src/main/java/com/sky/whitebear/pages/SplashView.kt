package com.sky.whitebear.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sky.whitebear.R
import com.sky.whitebear.date.SDK
import com.sky.whitebear.date.nav
import com.sky.whitebear.nav.NavUrl
import kotlinx.coroutines.delay

/**
 * 启动页
 * Created by ssk on 2022/4/17.
 */
@Preview
@Composable
fun SplashPage() {

    LaunchedEffect(Unit) {
        delay(1000)
        nav.popBackStack()
        val isLogin = SDK.isLogin()
        nav.navigate(if (isLogin) NavUrl.LOGIN else NavUrl.HOME)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(0.8f)),
        contentAlignment = Alignment.Center
    ) {


        Image(
            painter = painterResource(id = R.drawable.ic_launcher_playstore),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(50)),
        )
    }

}