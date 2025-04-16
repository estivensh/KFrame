package io.github.estivensh.kframe.devices.generic.laptop

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import io.github.estivensh.kframe.info.DefaultDeviceInfo
import io.github.estivensh.kframe.info.DeviceIdentifier
import io.github.estivensh.kframe.info.DeviceInfo
import io.github.estivensh.kframe.info.DeviceType
import io.github.estivensh.kframe.info.EdgeInsets
import io.github.estivensh.kframe.info.TargetPlatform

/**
 * Creates a generic "Laptop" device with the provided configurations.
 *
 * This function builds a [DeviceInfo] object with the device's configuration, including its platform,
 * identifier, screen size, safe areas, pixel ratio, and an optional frame painter.
 *
 * @param platform The platform of the device (e.g., [TargetPlatform.MACOS]).
 * @param id The unique identifier for the device.
 * @param name The name of the device.
 * @param screenSize The size of the device's screen.
 * @param windowPosition The position of the window area within the screen.
 * @param safeAreas The safe areas of the device (default is [EdgeInsets.Zero]).
 * @param pixelRatio The pixel ratio of the device (default is 2.0f).
 * @param rotatedSafeAreas The safe areas of the device when rotated (default is null).
 * @param framePainter An optional frame painter for the device. If not provided, a default one will be used.
 *
 * @return A [DeviceInfo] object representing the device with its configurations and frame representation.
 */
fun buildGenericLaptopDevice(
    platform: TargetPlatform,
    id: String,
    name: String,
    screenSize: Size,
    windowPosition: Rect,
    safeAreas: EdgeInsets = EdgeInsets.Zero,
    pixelRatio: Float = 2.0f,
    rotatedSafeAreas: EdgeInsets? = null,
    framePainter: GenericLaptopFramePainter? = null
): DeviceInfo {
    val effectivePainter = framePainter ?: GenericLaptopFramePainter(
        platform = platform,
        windowPosition = windowPosition
    )

    return DefaultDeviceInfo(
        identifier = DeviceIdentifier(
            platform = platform,
            type = DeviceType.LAPTOP,
            name = id
        ),
        name = name,
        pixelRatio = pixelRatio,
        frameSize = effectivePainter.calculateFrameSize(screenSize),
        screenSize = effectivePainter.effectiveWindowSize,
        safeAreas = safeAreas,
        rotatedSafeAreas = rotatedSafeAreas,
        framePainter = effectivePainter,
        screenPath = effectivePainter.createScreenPath(screenSize)
    )
}
