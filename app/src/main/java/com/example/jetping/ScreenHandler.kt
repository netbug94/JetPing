package com.example.jetping

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.jetping.view.MainScreenL
import com.example.jetping.view.MainScreenP

@Composable
fun ScreenHandler() {
    val screenConfig = LocalConfiguration.current
    val flipScreen = screenConfig.orientation == Configuration.ORIENTATION_PORTRAIT

    if (flipScreen) {
        MainScreenP()
    } else {
        MainScreenL()
    }
}