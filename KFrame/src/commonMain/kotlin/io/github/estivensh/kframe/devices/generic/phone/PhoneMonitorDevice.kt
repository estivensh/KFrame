package io.github.estivensh.kframe.devices.generic.phone

import androidx.compose.ui.geometry.Size
import io.github.estivensh.kframe.devices.generic.CustomPainter
import io.github.estivensh.kframe.info.DefaultDeviceInfo
import io.github.estivensh.kframe.info.DeviceIdentifier
import io.github.estivensh.kframe.info.DeviceInfo
import io.github.estivensh.kframe.info.DeviceType
import io.github.estivensh.kframe.info.EdgeInsets
import io.github.estivensh.kframe.info.TargetPlatform

/**
 * Builds a generic phone device representation.
 *
 * This function creates a `DeviceInfo` object for a generic phone, including the screen size,
 * safe areas, frame painter, and other properties. It uses a default implementation of a frame painter
 * (`GenericPhoneFramePainter`) to handle the visual representation of the phone's body and screen.
 *
 * @param platform The target platform of the device (e.g., Android, iOS).
 * @param id The unique identifier for the device.
 * @param name The name of the device.
 * @param screenSize The size of the screen of the device.
 * @param safeAreas The safe areas of the device (default is `EdgeInsets.Zero`).
 * @param rotatedSafeAreas The rotated safe areas, if applicable (default is `null`).
 * @param pixelRatio The pixel ratio for the device (default is `2.0f`).
 * @param framePainter The custom painter to render the device's frame (default is `GenericPhoneFramePainter`).
 *
 * @return A `DeviceInfo` object representing the generic phone device.
 */
fun buildGenericPhoneDevice(
    platform: TargetPlatform,
    id: String,
    name: String,
    screenSize: Size,
    safeAreas: EdgeInsets = EdgeInsets.Zero,
    rotatedSafeAreas: EdgeInsets? = null,
    pixelRatio: Float = 2.0f,
    framePainter: CustomPainter = GenericPhoneFramePainter()
): DeviceInfo {
    return DefaultDeviceInfo(
        identifier = DeviceIdentifier(
            platform = platform,
            type = DeviceType.PHONE,
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
