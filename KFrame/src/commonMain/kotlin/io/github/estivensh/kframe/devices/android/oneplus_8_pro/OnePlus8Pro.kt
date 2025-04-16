package io.github.estivensh.kframe.devices.android.oneplus_8_pro

import androidx.compose.ui.geometry.Size
import io.github.estivensh.kframe.info.DefaultDeviceInfo
import io.github.estivensh.kframe.info.DeviceIdentifier
import io.github.estivensh.kframe.info.DeviceInfo
import io.github.estivensh.kframe.info.DeviceType
import io.github.estivensh.kframe.info.EdgeInsets
import io.github.estivensh.kframe.info.TargetPlatform

object OnePlus8Pro {
    fun create(): DeviceInfo {
        return DefaultDeviceInfo(
            identifier = DeviceIdentifier(
                platform = TargetPlatform.ANDROID,
                type = DeviceType.PHONE,
                name = "oneplus-8-pro"
            ),
            name = "OnePlus 8 Pro",
            pixelRatio = 4.0f,
            safeAreas = EdgeInsets(
                left = 0f,
                top = 40f,
                right = 0f,
                bottom = 20f
            ),
            rotatedSafeAreas = EdgeInsets(
                left = 40f,
                top = 24f,
                right = 40f,
                bottom = 0f
            ),
            framePainter = OnePlus8ProFramePainter(),
            screenPath = onePlus8ProScreenPath(),
            frameSize = Size(852f, 1865f),
            screenSize = Size(360f, 800f)
        )
    }
}