package com.example.jetping

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.jetping.engine_logic.JetPingViewModel
import com.example.jetping.view.MainScreenH
import com.example.jetping.view.MainScreenV

@Composable
fun ScreenHandler(viewModel: JetPingViewModel) {
    val screenConfig = LocalConfiguration.current
    val flipScreen = screenConfig.orientation == Configuration.ORIENTATION_PORTRAIT

    if (flipScreen) {
        MainScreenV(viewModel)
    } else {
        MainScreenH()
    }
}
