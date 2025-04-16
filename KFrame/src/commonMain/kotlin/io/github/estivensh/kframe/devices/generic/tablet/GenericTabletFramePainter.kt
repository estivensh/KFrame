package io.github.estivensh.kframe.devices.generic.tablet

import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import io.github.estivensh.kframe.devices.generic.CustomPainter
import io.github.estivensh.kframe.devices.generic.base.drawCamera
import io.github.estivensh.kframe.devices.generic.base.drawDeviceBody
import io.github.estivensh.kframe.devices.generic.base.drawSideButtons
import io.github.estivensh.kframe.info.EdgeInsets
import io.github.estivensh.kframe.info.SideButtonSide

/**
 * A [CustomPainter] implementation used to draw the visual frame of a generic tablet device.
 *
 * This painter is responsible for calculating the full frame size, creating the screen path
 * for clipping or layout, and rendering the tablet body, side buttons, and camera.
 *
 * It supports customization via constructor parameters and uses defaults for common dimensions and colors.
 *
 * @param outerBodyColor The color of the tablet's outer frame.
 * @param innerBodyColor The color of the tablet's inner frame (around the screen).
 * @param outerBodyRadius The corner radius of the outer frame.
 * @param innerBodyRadius The corner radius of the inner frame.
 * @param innerBodyInsets Insets between the outer and inner frames.
 * @param screenInsets Insets between the inner frame and the actual screen area.
 * @param buttonWidth The width of the side buttons.
 * @param buttonColor The color of the side buttons.
 * @param screenRadius The corner radius of the screen.
 * @param rightSideButtonsGapsAndSizes List of gap and size values for buttons on the right side.
 * @param topSideButtonsGapsAndSizes List of gap and size values for buttons on the top side.
 * @param cameraBorderColor Color of the outer circle of the camera.
 * @param cameraInnerColor Color of the camera lens.
 * @param cameraReflectColor Highlight color to simulate camera reflection.
 * @param cameraRadius Radius of the camera lens.
 * @param cameraBorderWidth Width of the border surrounding the camera lens.
 */
class GenericTabletFramePainter(
    private val outerBodyColor: Color = defaultOuterBodyColor,
    private val innerBodyColor: Color = defaultInnerBodyColor,
    private val outerBodyRadius: CornerRadius = defaultOuterBodyRadius,
    private val innerBodyRadius: CornerRadius = defaultInnerBodyRadius,
    private val innerBodyInsets: EdgeInsets = defaultInnerBodyInsets,
    private val screenInsets: EdgeInsets = defaultScreenInsets,
    private val buttonWidth: Float = defaultButtonWidth,
    private val buttonColor: Color = defaultButtonColor,
    private val screenRadius: CornerRadius = defaultScreenRadius,
    private val rightSideButtonsGapsAndSizes: List<Float> = defaultRightSideButtonsGapsAndSizes,
    private val topSideButtonsGapsAndSizes: List<Float> = defaultTopSideButtonsGapsAndSizes,
    private val cameraBorderColor: Color = defaultCameraBorderColor,
    private val cameraInnerColor: Color = defaultCameraInnerColor,
    private val cameraReflectColor: Color = defaultCameraReflectColor,
    private val cameraRadius: Float = defaultCameraRadius,
    private val cameraBorderWidth: Float = defaultCameraBorderWidth
) : CustomPainter {

    /**
     * Calculates the full frame size based on the screen size and insets.
     */
    override fun calculateFrameSize(screenSize: Size): Size {
        return Size(
            width = screenSize.width +
                    innerBodyInsets.horizontal +
                    screenInsets.horizontal +
                    buttonWidth,
            height = screenSize.height +
                    innerBodyInsets.vertical +
                    screenInsets.vertical +
                    buttonWidth
        )
    }

    /**
     * Creates a rounded path for the screen area based on screen insets and radius.
     */
    override fun createScreenPath(screenSize: Size): Path {
        val rect = Rect(
            left = innerBodyInsets.left + screenInsets.left,
            top = innerBodyInsets.top + screenInsets.top,
            right = innerBodyInsets.left + screenInsets.left + screenSize.width,
            bottom = innerBodyInsets.top + screenInsets.top + screenSize.height
        )
        return Path().apply {
            addRoundRect(
                RoundRect(
                    rect = rect,
                    cornerRadius = screenRadius
                )
            )
        }
    }

    /**
     * Draws the entire tablet device, including:
     * - Outer and inner frames
     * - Top and right side buttons
     * - Centered front-facing camera
     */
    override fun draw(drawScope: DrawScope) {
        with(drawScope) {
            val paint = Paint().apply { style = PaintingStyle.Fill }
            val bounds = Rect(
                left = 0f,
                top = buttonWidth,
                right = size.width - buttonWidth,
                bottom = size.height - buttonWidth
            )

            // Draw top side buttons
            drawSideButtons(
                paint = paint,
                bounds = bounds,
                buttonWidth = buttonWidth,
                color = buttonColor,
                gapsAndSizes = topSideButtonsGapsAndSizes,
                inverted = true,
                side = SideButtonSide.TOP
            )

            // Draw right side buttons
            drawSideButtons(
                paint = paint,
                bounds = bounds,
                buttonWidth = buttonWidth,
                color = buttonColor,
                gapsAndSizes = rightSideButtonsGapsAndSizes,
                inverted = false,
                side = SideButtonSide.RIGHT
            )

            // Draw device body with outer and inner radii and colors
            drawDeviceBody(
                paint = paint,
                bounds = bounds,
                innerBodyColor = innerBodyColor,
                innerBodyInsets = innerBodyInsets,
                innerBodyRadius = innerBodyRadius,
                outerBodyColor = outerBodyColor,
                outerBodyRadius = outerBodyRadius
            )

            // Draw front-facing camera
            drawCamera(
                paint = paint,
                center = Offset(
                    x = (size.width - buttonWidth) * 0.5f,
                    y = buttonWidth + innerBodyInsets.top + (screenInsets.top / 2f)
                ),
                borderColor = cameraBorderColor,
                borderWidth = cameraBorderWidth,
                innerColor = cameraInnerColor,
                radius = cameraRadius,
                reflectColor = cameraReflectColor
            )
        }
    }

    companion object {
        // Default values used for drawing if none are provided
        val defaultButtonColor = Color(0xff121515)
        val defaultOuterBodyColor = Color(0xff3A4245)
        val defaultOuterBodyRadius = CornerRadius(40f)
        val defaultInnerBodyColor = Color(0xff121515)
        val defaultCameraBorderColor = Color(0xff262C2D)
        val defaultCameraInnerColor = Color(0xff121515)
        val defaultCameraReflectColor = Color(0xff465256)
        val defaultInnerBodyRadius = CornerRadius(35f)
        val defaultScreenRadius = CornerRadius(10f)
        const val defaultButtonWidth = 4f
        const val defaultCameraRadius = 6f
        const val defaultCameraBorderWidth = 4f

        val defaultRightSideButtonsGapsAndSizes = listOf(100f, 80f, 15f, 80f)
        val defaultTopSideButtonsGapsAndSizes = listOf(50f, 80f)

        val defaultInnerBodyInsets = EdgeInsets(
            left = 6f,
            top = 6f,
            right = 6f,
            bottom = 6f
        )
        val defaultScreenInsets = EdgeInsets(
            left = 25f,
            top = 120f,
            right = 25f,
            bottom = 80f
        )
    }
}
