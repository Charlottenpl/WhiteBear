package com.sky.whitebear.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.sky.whitebear.date.*

/**
 * 自定义主题
 * @param type      主题类型
 * @param darkTheme 是否开启夜间主题，
 *                  如果关闭传入false，
 *                  开启传入true，
 *                  不传的话按时间开启
 */
@Composable
fun WhiteBearTheme(type: Int = 1, darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

//    // Dynamic color is available on Android 12+
//    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
//    val colorScheme = when {
//        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
//        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
//        darkTheme -> darkColorScheme()
//        else -> lightColorScheme()
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = typography,
//        shapes = shapes,
//        content = content
//    )

    if (darkTheme && model.setting_is_system_dark) {
        //Dark
        MaterialTheme(
            colorScheme = darkColorScheme(),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    } else {
        //切换主题
        when(type){
            theme_light -> {
                MaterialTheme(
                    colorScheme = lightColorScheme(),
                    typography = Typography,
                    shapes = Shapes,
                    content = content
                )
            }
            theme_dark -> {
                MaterialTheme(
                    colorScheme = darkColorScheme(),
                    typography = Typography,
                    shapes = Shapes,
                    content = content
                )
            }
            theme_spring -> {
                MaterialTheme(
                    colorScheme = SpringColor,
                    typography = SpringTypography,
                    shapes = SpringShapes,
                    content = content
                )
            }
            theme_summer -> {
                MaterialTheme(
                    colorScheme = SummerColor,
                    typography = SummerTypography,
                    shapes = SummerShapes,
                    content = content
                )
            }
            theme_autumn -> {
                MaterialTheme(
                    colorScheme = AutumnColor,
                    typography = SummerTypography,
                    shapes = SummerShapes,
                    content = content
                )
            }
            theme_winter -> {
                MaterialTheme(
                    colorScheme = WinterColor,
                    typography = SummerTypography,
                    shapes = SummerShapes,
                    content = content
                )
            }
            else -> {
                MaterialTheme(
                    colorScheme = lightColorScheme(),
                    typography = Typography,
                    shapes = Shapes,
                    content = content
                )
            }
        }
    }
}