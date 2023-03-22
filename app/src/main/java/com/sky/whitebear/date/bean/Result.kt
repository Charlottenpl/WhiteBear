package com.sky.whitebear.date.bean

import androidx.annotation.Keep
import java.io.Serializable


@Keep
open class BaseResult(val code: Int = 0, val msg: String? = null) : Serializable