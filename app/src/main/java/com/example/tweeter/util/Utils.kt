package com.example.tweeter.util

import android.graphics.Color
import java.util.*

class Utils {

    companion object {
        @JvmStatic
        fun randomColor() : Int {
            var rnd = Random();
            return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        }
    }
}