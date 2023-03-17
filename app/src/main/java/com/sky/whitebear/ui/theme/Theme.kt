package com.sky.whitebear.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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

    if (darkTheme && setting_is_system_dark) {
        //Dark
        MaterialTheme(
            colors = DarkColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    } else {
        //切换主题
        when(type){
            theme_light -> {
                MaterialTheme(
                    colors = LightColorPalette,
                    typography = Typography,
                    shapes = Shapes,
                    content = content
                )
            }
            theme_dark -> {
                MaterialTheme(
                    colors = DarkColorPalette,
                    typography = Typography,
                    shapes = Shapes,
                    content = content
                )
            }
            theme_spring -> {
                MaterialTheme(
                    colors = SpringColorPalette,
                    typography = SpringTypography,
                    shapes = SpringShapes,
                    content = content
                )
            }
            theme_summer -> {
                MaterialTheme(
                    colors = SummerColorPalette,
                    typography = SummerTypography,
                    shapes = SummerShapes,
                    content = content
                )
            }
            else -> {
                MaterialTheme(
                    colors = LightColorPalette,
                    typography = Typography,
                    shapes = Shapes,
                    content = content
                )
            }
        }
    }
}