package com.sky.whitebear.util

import android.app.Service
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import com.sky.whitebear.date.con

object System {

    open fun vibrator(mill: Long){
        var effect: VibrationEffect = VibrationEffect.createOneShot(mill, 1)
        val vibrator = con.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()){
            vibrator.vibrate(effect)
        }
    }
}