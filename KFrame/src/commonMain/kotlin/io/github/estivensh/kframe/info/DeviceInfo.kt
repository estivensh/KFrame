package io.github.estivensh.kframe.info

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import io.github.estivensh.kframe.devices.generic.CustomPainter
import io.github.estivensh.kframe.devices.generic.desktop_monitor.GenericDesktopMonitorFramePainter
import io.github.estivensh.kframe.devices.generic.desktop_monitor.buildGenericDesktopMonitorDevice
import io.github.estivensh.kframe.devices.generic.laptop.GenericLaptopFramePainter
import io.github.estivensh.kframe.devices.generic.laptop.buildGenericLaptopDevice
import io.github.estivensh.kframe.devices.generic.phone.GenericPhoneFramePainter
import io.github.estivensh.kframe.devices.generic.phone.buildGenericPhoneDevice
import io.github.estivensh.kframe.devices.generic.tablet.GenericTabletFramePainter
import io.github.estivensh.kframe.devices.generic.tablet.buildGenericTabletDevice

/**
 * Represents a device's visual and layout characteristics in a multiplatform environment.
 * Implementations define properties like screen size, safe areas, rotation capabilities, and rendering frames.
 */
sealed interface DeviceInfo {

    /** Unique identifier for the device. */
    val identifier: DeviceIdentifier

    /** Human-readable name of the device. */
    val name: String

    /**
     * Safe area insets when the device is rotated (e.g., landscape orientation).
     * If `null`, rotation is not supported.
     */
    val rotatedSafeAreas: EdgeInsets?

    /** Default safe area insets for the device. */
    val safeAreas: EdgeInsets

    /** The path representing the device screen shape (e.g., for rounded corners). */
    val screenPath: Path

    /** The device's screen pixel density (DPI scaling factor). */
    val pixelRatio: Float

    /** Painter used to render the device's outer frame. */
    val framePainter: CustomPainter

    /** The total size of the frame surrounding the screen. */
    val frameSize: Size

    /** The actual size of the screen inside the device frame. */
    val screenSize: Size

    /**
     * Indicates whether the device supports rotation.
     * A device is rotatable if it has defined [rotatedSafeAreas].
     */
    val canRotate: Boolean
        get() = rotatedSafeAreas != null

    /**
     * Determines if the device is considered to be in landscape orientation.
     *
     * @param orientation The current orientation of the device.
     * @return `true` if the device supports rotation and is currently in landscape orientation.
     */
    fun isLandscape(orientation: Orientation): Boolean {
        return canRotate && orientation == Orientation.Landscape
    }

    companion object {
        /**
         * Creates a generic tablet device with customizable properties.
         *
         * @param platform The target platform (e.g., Android, iOS).
         * @param id Unique device identifier.
         * @param name Display name of the device.
         * @param screenSize The size of the screen.
         * @param safeAreas Default safe areas.
         * @param rotatedSafeAreas Safe areas when rotated (landscape).
         * @param pixelRatio Screen pixel density.
         * @param framePainter Painter used to render the device frame.
         * @return A [DeviceInfo] instance representing a generic tablet.
         */
        fun genericTablet(
            platform: TargetPlatform,
            id: String,
            name: String,
            screenSize: Size,
            safeAreas: EdgeInsets = EdgeInsets.Companion.Zero,
            rotatedSafeAreas: EdgeInsets = EdgeInsets.Companion.Zero,
            pixelRatio: Float = 2.0f,
            framePainter: GenericTabletFramePainter = GenericTabletFramePainter()
        ): DeviceInfo = buildGenericTabletDevice(
            platform = platform,
            id = id,
            name = name,
            screenSize = screenSize,
            safeAreas = safeAreas,
            rotatedSafeAreas = rotatedSafeAreas,
            pixelRatio = pixelRatio,
            framePainter = framePainter
        )

        /**
         * Creates a generic phone device with customizable properties.
         */
        fun genericPhone(
            platform: TargetPlatform,
            id: String,
            name: String,
            screenSize: Size,
            safeAreas: EdgeInsets = EdgeInsets.Companion.Zero,
            rotatedSafeAreas: EdgeInsets = EdgeInsets.Companion.Zero,
            pixelRatio: Float = 2.0f,
            framePainter: GenericPhoneFramePainter = GenericPhoneFramePainter()
        ): DeviceInfo = buildGenericPhoneDevice(
            platform = platform,
            id = id,
            name = name,
            screenSize = screenSize,
            safeAreas = safeAreas,
            rotatedSafeAreas = rotatedSafeAreas,
            pixelRatio = pixelRatio,
            framePainter = framePainter
        )

        /**
         * Creates a generic desktop monitor with customizable properties.
         */
        fun genericDesktopMonitor(
            platform: TargetPlatform,
            id: String,
            name: String,
            screenSize: Size,
            windowPosition: Rect,
            safeAreas: EdgeInsets = EdgeInsets.Companion.Zero,
            pixelRatio: Float = 2.0f,
            framePainter: GenericDesktopMonitorFramePainter? = null
        ): DeviceInfo = buildGenericDesktopMonitorDevice(
            platform = platform,
            id = id,
            name = name,
            screenSize = screenSize,
            windowPosition = windowPosition,
            safeAreas = safeAreas,
            pixelRatio = pixelRatio,
            framePainter = framePainter
        )

        /**
         * Creates a generic laptop device with customizable properties.
         */
        fun genericLaptop(
            platform: TargetPlatform,
            id: String,
            name: String,
            screenSize: Size,
            windowPosition: Rect,
            safeAreas: EdgeInsets = EdgeInsets.Companion.Zero,
            pixelRatio: Float = 2.0f,
            framePainter: GenericLaptopFramePainter? = null
        ): DeviceInfo = buildGenericLaptopDevice(
            platform = platform,
            id = id,
            name = name,
            screenSize = screenSize,
            windowPosition = windowPosition,
            safeAreas = safeAreas,
            pixelRatio = pixelRatio,
            framePainter = framePainter
        )
    }
}

/**
 * Default implementation of [DeviceInfo] used to define the properties of a specific device.
 *
 * @property identifier Unique device identifier.
 * @property name Display name of the device.
 * @property rotatedSafeAreas Safe area insets when rotated (nullable).
 * @property safeAreas Default safe area insets.
 * @property screenPath Path representing the screen shape.
 * @property pixelRatio Screen density.
 * @property framePainter Painter for the device frame.
 * @property frameSize Total frame dimensions.
 * @property screenSize Screen dimensions inside the frame.
 */
data class DefaultDeviceInfo(
    override val identifier: DeviceIdentifier,
    override val name: String,
    override val rotatedSafeAreas: EdgeInsets?,
    override val safeAreas: EdgeInsets,
    override val screenPath: Path,
    override val pixelRatio: Float,
    override val framePainter: CustomPainter,
    override val frameSize: Size,
    override val screenSize: Size
) : DeviceInfo
