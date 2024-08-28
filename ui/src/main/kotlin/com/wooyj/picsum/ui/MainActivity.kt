package com.wooyj.picsum.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wooyj.picsum.ui.screen.main.MainScreen
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
