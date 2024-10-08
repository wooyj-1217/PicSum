package com.wooyj.picsum.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wooyj.picsum.feature.main.ui.MainScreen
import com.wooyj.picsum.ui.theme.PicSumTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicSumTheme {
                MainScreen()
            }
        }
    }
}
