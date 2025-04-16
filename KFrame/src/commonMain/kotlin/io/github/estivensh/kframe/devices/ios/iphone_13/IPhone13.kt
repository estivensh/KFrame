package io.github.estivensh.kframe.devices.ios.iphone_13

import androidx.compose.ui.geometry.Size
import io.github.estivensh.kframe.info.DefaultDeviceInfo
import io.github.estivensh.kframe.info.DeviceIdentifier
import io.github.estivensh.kframe.info.DeviceInfo
import io.github.estivensh.kframe.info.DeviceType
import io.github.estivensh.kframe.info.EdgeInsets
import io.github.estivensh.kframe.info.TargetPlatform

object IPhone13 {
    fun create(): DeviceInfo {
        return DefaultDeviceInfo(
            identifier = DeviceIdentifier(
                platform = TargetPlatform.IOS,
                type = DeviceType.PHONE,
                name = "iphone-13"
            ),
            name = "iPhone 13",
            pixelRatio = 3.0f,
            frameSize = Size(873f, 1771f),
            screenSize = Size(390f, 844f),
            safeAreas = EdgeInsets(
                left = 0f,
                top = 47f,
                right = 0f,
                bottom = 34f
            ),
            rotatedSafeAreas = EdgeInsets(
                left = 47f,
                top = 0f,
                right = 47f,
                bottom = 21f
            ),
            framePainter = IPhone13FramePainter(),
            screenPath = iPhone13ScreenPath()
        )
    }
}