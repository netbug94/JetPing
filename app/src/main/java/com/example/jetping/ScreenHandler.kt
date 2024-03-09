package com.example.jetping

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ScreenHandler() {
    val screenConfig = LocalConfiguration.current
    val flipScreen = screenConfig.orientation == Configuration.ORIENTATION_PORTRAIT

    if (flipScreen) = {
        MainScreenP()
    } else {
        MainScreenL()
    }
}