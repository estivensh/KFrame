package io.github.estivensh.kframe.devices.generic.phone

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import io.github.estivensh.kframe.devices.generic.CustomPainter
import io.github.estivensh.kframe.devices.generic.base.drawCamera
import io.github.estivensh.kframe.devices.generic.base.drawDeviceBody
import io.github.estivensh.kframe.devices.generic.base.drawSideButtons
import io.github.estivensh.kframe.info.EdgeInsets
import io.github.estivensh.kframe.info.SideButtonSide

/**
 * A painter class for drawing a generic phone frame, including body, buttons, and camera.
 *
 * This class is used to create and draw the frame of a phone device, including its body, screen, buttons, and camera,
 * based on various customizable parameters.
 *
 * @property outerBodyColor The color of the outer body of the phone.
 * @property innerBodyColor The color of the inner body of the phone.
 * @property outerBodyRadius The radius of the outer body corners.
 * @property innerBodyRadius The radius of the inner body corners.
 * @property innerBodyInsets Insets for the inner body area.
 * @property screenInsets Insets for the screen area.
 * @property buttonWidth The width of the side buttons.
 * @property buttonColor The color of the side buttons.
 * @property screenRadius The radius of the screen corners.
 * @property rightSideButtonsGapsAndSizes A list of gaps and sizes for the right-side buttons.
 * @property topSideButtonsGapsAndSizes A list of gaps and sizes for the top-side buttons.
 * @property cameraBorderColor The border color of the camera.
 * @property cameraInnerColor The inner color of the camera.
 * @property cameraReflectColor The reflection color of the camera.
 * @property cameraRadius The radius of the camera.
 * @property cameraBorderWidth The width of the camera border.
 */
class GenericPhoneFramePainter(
    private val outerBodyColor: Color = defaultOuterBodyColor,
    private val innerBodyColor: Color = defaultInnerBodyColor,
    private val outerBodyRadius: CornerRadius = defaultOuterBodyRadius,
    private val innerBodyRadius: CornerRadius = defaultInnerBodyRadius,
    private val innerBodyInsets: EdgeInsets = defaultInnerBodyInsets,
    private val screenInsets: EdgeInsets = defaultScreenInsets,
    private val buttonWidth: Float = DEFAULT_BUTTON_WIDTH,
    private val buttonColor: Color = defaultButtonColor,
    private val screenRadius: CornerRadius = defaultScreenRadius,
    private val rightSideButtonsGapsAndSizes: List<Float> = defaultRightSideButtonsGapsAndSizes,
    private val topSideButtonsGapsAndSizes: List<Float> = defaultTopSideButtonsGapsAndSizes,
    private val cameraBorderColor: Color = defaultCameraBorderColor,
    private val cameraInnerColor: Color = defaultCameraInnerColor,
    private val cameraReflectColor: Color = defaultCameraReflectColor,
    private val cameraRadius: Float = DEFAULT_CAMERA_RADIUS,
    private val cameraBorderWidth: Float = DEFAULT_CAMERA_BORDER_WIDTH
) : CustomPainter {

    /**
     * Calculates the total frame size by considering the screen size and the body and button insets.
     *
     * @param screenSize The size of the screen.
     * @return The total frame size, including the screen size and insets.
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
     * Creates the path for the screen shape inside the inner body with rounded corners.
     *
     * @param screenSize The size of the screen.
     * @return A [Path] representing the shape of the screen.
     */
    override fun createScreenPath(screenSize: Size): Path {
        val rect = Rect(
            left = innerBodyInsets.left + screenInsets.left,
            top = innerBodyInsets.top + screenInsets.top,
            right = innerBodyInsets.left + screenInsets.left + screenSize.width,
            bottom = innerBodyInsets.top + screenInsets.top + screenSize.height
        )
        return Path().apply {
            addRoundRect(RoundRect(rect = rect, cornerRadius = screenRadius))
        }
    }

    /**
     * Draws the phone body, buttons, and camera using the [DrawScope].
     *
     * This method handles the drawing of all the elements, including the device body, side buttons, and the front camera.
     *
     * @param drawScope The [DrawScope] used for drawing the device elements.
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

            // Top side buttons
            drawSideButtons(
                paint = paint,
                bounds = bounds,
                buttonWidth = buttonWidth,
                color = buttonColor,
                gapsAndSizes = topSideButtonsGapsAndSizes,
                inverted = true,
                side = SideButtonSide.TOP
            )

            // Right side buttons
            drawSideButtons(
                paint = paint,
                bounds = bounds,
                buttonWidth = buttonWidth,
                color = buttonColor,
                gapsAndSizes = rightSideButtonsGapsAndSizes,
                inverted = false,
                side = SideButtonSide.RIGHT
            )

            // Device body
            drawDeviceBody(
                paint = paint,
                bounds = bounds,
                innerBodyColor = innerBodyColor,
                innerBodyInsets = innerBodyInsets,
                innerBodyRadius = innerBodyRadius,
                outerBodyColor = outerBodyColor,
                outerBodyRadius = outerBodyRadius
            )

            // Front camera
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
        const val DEFAULT_BUTTON_WIDTH = 4f
        const val DEFAULT_CAMERA_RADIUS = 8f
        const val DEFAULT_CAMERA_BORDER_WIDTH = 5f
        val defaultButtonColor = Color(0xff121515)
        val defaultOuterBodyColor = Color(0xff3A4245)
        val defaultOuterBodyRadius = CornerRadius(40f)
        val defaultInnerBodyColor = Color(0xff121515)
        val defaultCameraBorderColor = Color(0xff262C2D)
        val defaultCameraInnerColor = Color(0xff121515)
        val defaultCameraReflectColor = Color(0xff465256)
        val defaultInnerBodyRadius = CornerRadius(35f)
        val defaultScreenRadius = CornerRadius(10f)
        val defaultRightSideButtonsGapsAndSizes = listOf(100f, 80f, 15f, 80f)
        val defaultTopSideButtonsGapsAndSizes = listOf(50f, 80f)

        val defaultInnerBodyInsets = EdgeInsets(
            left = 6f,
            top = 6f,
            right = 6f,
            bottom = 6f
        )
        val defaultScreenInsets = EdgeInsets(
            left = 15f,
            top = 80f,
            right = 15f,
            bottom = 60f
        )
    }
}
