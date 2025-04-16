package io.github.estivensh.kframe.devices.generic

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

/**
 * Interface that defines the contract for custom device frame painters.
 * Implement this interface to provide custom drawing logic for different types of device frames.
 */
interface CustomPainter {

    /**
     * Calculates the total size of the device frame based on the screen size.
     *
     * @param screenSize The size of the screen content area.
     * @return The overall size of the frame, including paddings, borders, and buttons.
     */
    fun calculateFrameSize(screenSize: Size): Size

    /**
     * Creates the path for the screen shape within the frame.
     *
     * @param screenSize The size of the screen content area.
     * @return A [Path] that defines the rounded rectangle or custom shape of the screen.
     */
    fun createScreenPath(screenSize: Size): Path

    /**
     * Draws the custom frame using the provided [DrawScope].
     * This method includes the logic for drawing body, camera, buttons, and screen path.
     *
     * @param drawScope The drawing context that provides the canvas and drawing functions.
     */
    fun draw(drawScope: DrawScope)
}
