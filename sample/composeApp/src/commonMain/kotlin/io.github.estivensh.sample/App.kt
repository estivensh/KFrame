package io.github.estivensh.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.estivensh.kframe.DeviceScreen
import io.github.estivensh.kframe.devices.ios.iphone_13.IPhone13
import io.github.estivensh.kframe.info.Orientation

@Composable
fun App() {
    val onePlus8Pro = IPhone13.create()

    DeviceScreen(
        device = onePlus8Pro,
        orientation = Orientation.Portrait,
        isFrameVisible = true
    ) {
        Box(Modifier.fillMaxSize().background(Color.Red))
    }
}