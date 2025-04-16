package io.github.estivensh.kframe.devices.generic.laptop

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import io.github.estivensh.kframe.devices.generic.CustomPainter
import io.github.estivensh.kframe.devices.generic.base.drawCamera
import io.github.estivensh.kframe.devices.generic.base.drawDefaultWallpaper
import io.github.estivensh.kframe.devices.generic.base.drawWindowBar
import io.github.estivensh.kframe.info.EdgeInsets
import io.github.estivensh.kframe.info.TargetPlatform

/**
 * Custom painter class to draw a generic laptop frame including the body, screen, camera, and window elements.
 * This class takes into account platform-specific attributes and dimensions for proper rendering.
 *
 * @param platform The target platform for rendering (e.g., macOS, Windows).
 * @param windowPosition The position and size of the window within the frame.
 * @param outerBodyColor Color of the outer body of the laptop.
 * @param innerBodyColor Color of the inner body of the laptop.
 * @param outerBodyRadius Corner radius for the outer body.
 * @param innerBodyRadius Corner radius for the inner body.
 * @param innerBodyInsets Insets for the inner body of the laptop.
 * @param screenInsets Insets for the screen.
 * @param screenRadius Corner radius for the screen.
 * @param windowRadius Corner radius for the window.
 * @param bodyHeight Height of the body of the laptop.
 * @param bodyPadHeight Height of the body padding.
 * @param bodyInsets General insets for the body.
 * @param cameraBorderColor Color of the camera's border.
 * @param cameraInnerColor Color of the inner part of the camera.
 * @param cameraReflectColor Color of the camera reflection.
 * @param cameraRadius Radius of the camera.
 * @param cameraBorderWidth Width of the camera's border.
 * @param baseBodyTopColor Color of the top portion of the base body.
 * @param baseBodyBottomColor Color of the bottom portion of the base body.
 * @param baseBodyPadColor Color of the padding on the base body.
 */
class GenericLaptopFramePainter(
    private val platform: TargetPlatform,
    private val windowPosition: Rect,
    private val outerBodyColor: Color = defaultOuterBodyColor,
    private val innerBodyColor: Color = defaultInnerBodyColor,
    private val outerBodyRadius: CornerRadius = defaultOuterBodyRadius,
    private val innerBodyRadius: CornerRadius = defaultInnerBodyRadius,
    private val innerBodyInsets: EdgeInsets = defaultInnerBodyInsets,
    private val screenInsets: EdgeInsets = defaultScreenInsets,
    private val screenRadius: CornerRadius = defaultScreenRadius,
    private val windowRadius: CornerRadius = defaultWindowRadius,
    private val bodyHeight: Float = DEFAULT_BODY_HEIGHT,
    private val bodyPadHeight: Float = DEFAULT_BODY_PAD_HEIGHT,
    private val bodyInsets: Float = DEFAULT_BODY_INSETS,
    private val cameraBorderColor: Color = defaultCameraBorderColor,
    private val cameraInnerColor: Color = defaultCameraInnerColor,
    private val cameraReflectColor: Color = defaultCameraReflectColor,
    private val cameraRadius: Float = DEFAULT_CAMERA_RADIUS,
    private val cameraBorderWidth: Float = DEFAULT_CAMERA_BORDER_WIDTH,
    private val baseBodyTopColor: Color = defaultBaseBodyTopColor,
    private val baseBodyBottomColor: Color = defaultBaseBodyBottomColor,
    private val baseBodyPadColor: Color = defaultBaseBodyPadColor
) : CustomPainter {

    /**
     * Calculates the size of the frame, taking into account the screen size and insets.
     *
     * @param screenSize The size of the screen.
     * @return The calculated size of the frame.
     */
    override fun calculateFrameSize(screenSize: Size): Size {
        return Size(
            width = screenSize.width +
                    innerBodyInsets.horizontal +
                    screenInsets.horizontal +
                    DEFAULT_BODY_INSETS * 2,
            height = screenSize.height +
                    innerBodyInsets.vertical +
                    screenInsets.vertical +
                    bodyHeight +
                    bodyPadHeight
        )
    }

    /**
     * Returns the effective size of the window, excluding the window bar height.
     */
    val effectiveWindowSize: Size
        get() = Size(
            width = windowPosition.width,
            height = windowPosition.height - barHeight
        )

    /**
     * Returns the height of the window bar based on the platform.
     */
    private val barHeight: Float
        get() = when (platform) {
            TargetPlatform.MACOS -> 30f
            else -> 40f
        }

    /**
     * Returns the offset for the window position based on insets and window position.
     */
    private val windowLocation: Offset
        get() = Offset(
            x = DEFAULT_BODY_INSETS +
                    innerBodyInsets.left +
                    screenInsets.left +
                    windowPosition.left,
            y = innerBodyInsets.top +
                    screenInsets.top +
                    windowPosition.top
        )

    /**
     * Creates the path for the screen, including the screen's rounded corners.
     *
     * @param screenSize The size of the screen.
     * @return The path representing the screen.
     */
    override fun createScreenPath(screenSize: Size): Path {
        return Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(
                        offset = windowLocation + Offset(0f, barHeight),
                        size = effectiveWindowSize
                    ),
                    cornerRadius = CornerRadius(
                        x = windowRadius.x,
                        y = windowRadius.x
                    )
                )
            )
        }
    }

    /**
     * Draws the laptop frame including the outer and inner body, camera, and base components.
     *
     * @param drawScope The scope in which the drawing operations take place.
     */
    override fun draw(drawScope: DrawScope) {
        with(drawScope) {
            val paint = Paint().apply { style = PaintingStyle.Fill }
            val bounds = Rect(
                left = DEFAULT_BODY_INSETS,
                top = 0f,
                right = size.width - DEFAULT_BODY_INSETS,
                bottom = size.height - bodyPadHeight - bodyHeight
            )

            // Draw outer body
            drawRoundRect(
                color = outerBodyColor,
                topLeft = bounds.topLeft,
                size = bounds.size,
                cornerRadius = outerBodyRadius,
                style = Fill
            )

            // Draw inner body
            val innerBounds = Rect(
                left = bounds.left + innerBodyInsets.left,
                top = bounds.top + innerBodyInsets.top,
                right = bounds.right - innerBodyInsets.right,
                bottom = bounds.bottom - innerBodyInsets.bottom
            )
            drawRoundRect(
                color = innerBodyColor,
                topLeft = innerBounds.topLeft,
                size = innerBounds.size,
                cornerRadius = innerBodyRadius,
                style = Fill
            )

            // Draw camera
            drawCamera(
                paint = paint,
                center = Offset(
                    x = bounds.center.x,
                    y = innerBodyInsets.top + (screenInsets.top / 2f)
                ),
                borderColor = cameraBorderColor,
                borderWidth = cameraBorderWidth,
                innerColor = cameraInnerColor,
                radius = cameraRadius,
                reflectColor = cameraReflectColor
            )

            // Draw base body components
            drawBaseBodyComponents(bounds)
        }
    }

    /**
     * Draws the base components of the laptop frame, including the top and bottom sections, as well as the pad.
     *
     * @param bounds The bounds of the body.
     */
    private fun DrawScope.drawBaseBodyComponents(bounds: Rect) {
        val padRadius = CornerRadius(x = bodyPadHeight, y = bodyPadHeight)
        drawRoundRect(
            color = baseBodyPadColor,
            topLeft = Offset(
                x = outerBodyRadius.x + bodyPadHeight * 4f,
                y = bounds.bottom + bodyHeight - 1f
            ),
            size = Size(
                width = size.width - 2 * outerBodyRadius.x - bodyPadHeight * 8f,
                height = bodyPadHeight + 1f
            ),
            cornerRadius = padRadius,
            style = Fill
        )

        drawRoundRect(
            color = baseBodyBottomColor,
            topLeft = Offset(0f, bounds.bottom + bodyHeight * 0.2f - 1f),
            size = Size(size.width, bodyHeight * 0.8f + 1f),
            cornerRadius = outerBodyRadius,
            style = Fill
        )

        drawRoundRect(
            color = baseBodyTopColor,
            topLeft = Offset(0f, bounds.bottom),
            size = Size(size.width, bodyHeight * 0.2f),
            cornerRadius = CornerRadius(
                x = innerBodyRadius.x * 0.5f,
                y = innerBodyRadius.x * 0.5f
            ),
            style = Fill
        )

        val bodyNotchWidth = 300f
        drawRoundRect(
            color = baseBodyTopColor,
            topLeft = Offset(
                x = bounds.center.x - bodyNotchWidth * 0.5f,
                y = bounds.bottom + bodyHeight * 0.2f - 1f
            ),
            size = Size(bodyNotchWidth, bodyHeight * 0.2f),
            cornerRadius = CornerRadius(
                x = bodyHeight * 0.2f,
                y = bodyHeight * 0.2f
            ),
            style = Fill
        )

        drawScreenContent(bounds)
    }

    /**
     * Draws the content of the screen, including a wallpaper and any window shadows.
     *
     * @param bounds The bounds of the screen area.
     */
    private fun DrawScope.drawScreenContent(bounds: Rect) {
        val screenBounds = Rect(
            left = DEFAULT_BODY_INSETS + innerBodyInsets.left + screenInsets.left,
            top = innerBodyInsets.top + screenInsets.top,
            right = bounds.right - innerBodyInsets.right - screenInsets.right,
            bottom = bounds.bottom - innerBodyInsets.bottom - screenInsets.bottom
        )

        drawIntoCanvas { canvas ->
            canvas.save()
            canvas.clipPath(
                Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = screenBounds,
                            cornerRadius = screenRadius
                        )
                    )
                }
            )
            drawDefaultWallpaper(platform, screenBounds)
            canvas.restore()
        }

        drawWindowWithShadow()
        drawWindowBar()
    }

    /**
     * Draws the window with a shadow effect to simulate depth.
     */
    private fun DrawScope.drawWindowWithShadow() {
        val windowPath = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(offset = windowLocation, size = effectiveWindowSize),
                    cornerRadius = windowRadius
                )
            )
        }
        drawPath(
            path = windowPath,
            color = Color(0xFF3F2548).copy(alpha = 0.6f),
            blendMode = BlendMode.Multiply,
            style = Fill
        )
    }

    /**
     * Draws the window bar at the top of the window.
     */
    private fun DrawScope.drawWindowBar() {
        drawWindowBar(
            platform = platform,
            bounds = Rect(
                offset = windowLocation,
                size = Size(windowPosition.width, barHeight)
            ),
            windowRadius = windowRadius
        )
    }

    companion object {
        // Default values for various properties
        val defaultBaseBodyTopColor = Color(0xff3A4245)
        val defaultBaseBodyBottomColor = Color(0xff323434)
        val defaultBaseBodyPadColor = Color(0xff222626)
        val defaultOuterBodyColor = Color(0xff3A4245)
        val defaultOuterBodyRadius = CornerRadius(30f)
        val defaultInnerBodyColor = Color(0xff121515)
        val defaultInnerBodyRadius = CornerRadius(20f)
        val defaultScreenRadius = CornerRadius(10f)
        val defaultWindowRadius = CornerRadius(6f)
        val defaultCameraBorderColor = Color(0xff262C2D)
        val defaultCameraInnerColor = Color(0xff121515)
        val defaultCameraReflectColor = Color(0xff465256)
        const val DEFAULT_CAMERA_RADIUS = 8f
        const val DEFAULT_CAMERA_BORDER_WIDTH = 5f
        const val DEFAULT_BODY_HEIGHT = 60f
        const val DEFAULT_BODY_PAD_HEIGHT = 16f
        const val DEFAULT_BODY_INSETS = 200f

        val defaultInnerBodyInsets = EdgeInsets(
            left = 6f,
            top = 6f,
            right = 6f,
            bottom = 0f
        )
        val defaultScreenInsets = EdgeInsets(
            left = 20f,
            top = 80f,
            right = 20f,
            bottom = 100f
        )
    }
}
