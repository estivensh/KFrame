package io.github.estivensh.kframe.info

import androidx.compose.ui.geometry.Size

/**
 * Represents the orientation of a device or screen.
 */
enum class Orientation {
    /** Portrait orientation (vertical). */
    Portrait,

    /** Landscape orientation (horizontal). */
    Landscape;

    companion object {
        /**
         * Determines the [Orientation] based on the given [Size].
         *
         * @param size The size of the screen or layout.
         * @return [Portrait] if the height is greater than or equal to the width; otherwise [Landscape].
         */
        fun fromSize(size: Size): Orientation {
            return if (size.height >= size.width) Portrait else Landscape
        }

        /**
         * Determines if two orientations are opposite to each other.
         *
         * @param first The first orientation.
         * @param second The second orientation.
         * @return `true` if the orientations are different; otherwise `false`.
         */
        fun areOpposite(first: Orientation, second: Orientation): Boolean {
            return first != second
        }
    }
}

/**
 * Returns `true` if the orientation is [Portrait].
 */
fun Orientation.isPortrait(): Boolean = this == Orientation.Portrait

/**
 * Returns `true` if the orientation is [Landscape].
 */
fun Orientation.isLandscape(): Boolean = this == Orientation.Landscape

/**
 * Determines if the device is in landscape mode based on the given [orientation]
 * and the actual screen dimensions.
 *
 * @receiver The [DeviceInfo] instance to check.
 * @param orientation The current orientation.
 * @return `true` if the orientation is landscape and the screen's width is greater than its height.
 */
fun DeviceInfo.isInLandscape(orientation: Orientation): Boolean {
    return orientation.isLandscape() && this.screenSize.width > this.screenSize.height
}

/**
 * Returns the screen size of the device adjusted for the given [orientation].
 *
 * @receiver The [DeviceInfo] instance.
 * @param orientation The current orientation.
 * @return A [Size] object representing the oriented screen dimensions.
 *         If landscape, width and height are swapped.
 */
fun DeviceInfo.getOrientedScreenSize(orientation: Orientation): Size {
    return if (orientation.isLandscape()) {
        Size(this.screenSize.height, this.screenSize.width)
    } else {
        this.screenSize
    }
}