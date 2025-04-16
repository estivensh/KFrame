package io.github.estivensh.kframe.devices.generic.desktop_monitor

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import io.github.estivensh.kframe.info.DefaultDeviceInfo
import io.github.estivensh.kframe.info.DeviceIdentifier
import io.github.estivensh.kframe.info.DeviceInfo
import io.github.estivensh.kframe.info.DeviceType
import io.github.estivensh.kframe.info.EdgeInsets
import io.github.estivensh.kframe.info.TargetPlatform

/**
 * Builds a generic desktop monitor [DeviceInfo] instance for preview or simulation purposes.
 *
 * @param platform The target platform (e.g., Windows, macOS, Linux).
 * @param id A unique identifier for the device.
 * @param name A human-readable name for the device.
 * @param screenSize The logical screen size of the device (excluding frame).
 * @param windowPosition The position and size of the window within the monitor frame.
 * @param safeAreas Insets representing the safe display areas on the screen.
 * @param pixelRatio The device's pixel density (e.g., 2.0 for Retina-like screens).
 * @param rotatedSafeAreas Optional safe area values for landscape orientation.
 * @param framePainter An optional custom frame painter. If not provided, a default one is used.
 *
 * @return A configured [DeviceInfo] representing a generic desktop monitor.
 */
fun buildGenericDesktopMonitorDevice(
    platform: TargetPlatform,
    id: String,
    name: String,
    screenSize: Size,
    windowPosition: Rect,
    safeAreas: EdgeInsets = EdgeInsets.Zero,
    pixelRatio: Float = 2.0f,
    rotatedSafeAreas: EdgeInsets? = null,
    framePainter: GenericDesktopMonitorFramePainter? = null
): DeviceInfo {
    val effectivePainter = framePainter ?: GenericDesktopMonitorFramePainter(
        platform = platform,
        windowPosition = windowPosition
    )

    return DefaultDeviceInfo(
        identifier = DeviceIdentifier(
            platform = platform,
            type = DeviceType.DESKTOP,
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
