package com.sky.whitebear.nav

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.sky.whitebear.pages.LoginPage
import com.sky.whitebear.pages.SplashPage
import com.sky.whitebear.skyView

/**
 * Nav导航图
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = NavUrl.SPLASH,
    onFinish: () -> Unit = { }
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavUrl.SPLASH) {
            SplashPage()
        }
        composable(NavUrl.LOGIN) {
            LoginPage()
        }
        composable(NavUrl.HOME) {
             //Home页面
            skyView()
        }
    }
}
