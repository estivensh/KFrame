package io.github.estivensh.kframe.devices.generic.tablet

import androidx.compose.ui.geometry.Size
import io.github.estivensh.kframe.devices.generic.CustomPainter
import io.github.estivensh.kframe.info.DefaultDeviceInfo
import io.github.estivensh.kframe.info.DeviceIdentifier
import io.github.estivensh.kframe.info.DeviceInfo
import io.github.estivensh.kframe.info.DeviceType
import io.github.estivensh.kframe.info.EdgeInsets
import io.github.estivensh.kframe.info.TargetPlatform

/**
 * Builds a generic tablet device representation.
 *
 * This function creates a `DeviceInfo` object for a generic tablet. It includes the screen size,
 * safe areas, pixel density, and a frame painter that draws the device frame.
 * By default, it uses the `GenericTabletFramePainter` to handle the frame drawing.
 *
 * @param platform The target platform of the device (e.g., Android, iOS).
 * @param id The unique identifier for the device.
 * @param name The display name of the device.
 * @param screenSize The size of the device's screen.
 * @param safeAreas The safe areas within the screen to avoid content being cut off (default is `EdgeInsets.Zero`).
 * @param rotatedSafeAreas Optional safe areas when the device is rotated (default is `null`).
 * @param pixelRatio The screen pixel density ratio (default is `2.0f`).
 * @param framePainter The custom painter used to draw the tablet frame (default is `GenericTabletFramePainter`).
 *
 * @return A `DeviceInfo` instance representing the generic tablet device.
 */
fun buildGenericTabletDevice(
    platform: TargetPlatform,
    id: String,
    name: String,
    screenSize: Size,
    safeAreas: EdgeInsets = EdgeInsets.Zero,
    rotatedSafeAreas: EdgeInsets? = null,
    pixelRatio: Float = 2.0f,
    framePainter: CustomPainter = GenericTabletFramePainter()
): DeviceInfo {
    return DefaultDeviceInfo(
        identifier = DeviceIdentifier(
            platform = platform,
            type = DeviceType.TABLET,
            name = id
        ),
        name = name,
        rotatedSafeAreas = rotatedSafeAreas,
        safeAreas = safeAreas,
        screenPath = framePainter.createScreenPath(screenSize),
        pixelRatio = pixelRatio,
        framePainter = framePainter,
        frameSize = framePainter.calculateFrameSize(screenSize),
        screenSize = screenSize
    )
}
