package com.sky.whitebear.date

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.sky.whitebear.SkyViewModel

//static date
var model: SkyViewModel = SkyViewModel()


//NavId
const val nav_home = 0;
const val nav_music = 1;
const val nav_chat = 2;
const val nav_setting = 3;


//ThemeType
var theme_dark    = 1
var theme_light   = 2
var theme_spring  = 3
var theme_summer  = 4
var theme_autumn  = 5
var theme_winter  = 6


//setting
var setting_is_system_dark = true; //是否跟随系统夜间模式
var setting_theme_id = 4; //当前主题


//chat status
var chat_text = 0
var chat_img = 1
var chat_music = 2
var chat_file = 3