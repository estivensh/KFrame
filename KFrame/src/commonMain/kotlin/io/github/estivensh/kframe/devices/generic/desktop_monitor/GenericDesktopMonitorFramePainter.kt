package io.github.estivensh.kframe.devices.generic.desktop_monitor

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
import io.github.estivensh.kframe.devices.generic.base.drawDefaultWallpaper
import io.github.estivensh.kframe.devices.generic.base.drawDeviceBody
import io.github.estivensh.kframe.devices.generic.base.drawMonitorFoot
import io.github.estivensh.kframe.devices.generic.base.drawWindowBar
import io.github.estivensh.kframe.info.EdgeInsets
import io.github.estivensh.kframe.info.TargetPlatform

/**
 * Custom painter that draws a desktop monitor frame with a window overlay on top of the screen.
 *
 * This painter is fully customizable, allowing you to configure colors, corner radii, insets,
 * foot dimensions, and platform-specific visual details like the window bar height.
 *
 * @param platform Target platform (e.g., MACOS, WINDOWS).
 * @param windowPosition The position and size of the floating window inside the screen.
 * @param outerBodyColor Color of the outer monitor frame.
 * @param innerBodyColor Color of the inner monitor frame.
 * @param outerBodyRadius Corner radius of the outer frame.
 * @param innerBodyRadius Corner radius of the inner frame.
 * @param innerBodyInsets Padding between the outer and inner frame.
 * @param screenInsets Padding between the inner frame and the screen.
 * @param screenRadius Corner radius of the screen.
 * @param footSize Size of the monitor's foot (width and height).
 * @param footBarWidth Width of the vertical bar connecting the monitor to the foot.
 * @param footBaseHeight Height of the foot base.
 * @param windowRadius Corner radius of the floating window inside the screen.
 */
class GenericDesktopMonitorFramePainter(
    private val platform: TargetPlatform,
    private val windowPosition: Rect,
    private val outerBodyColor: Color = defaultOuterBodyColor,
    private val innerBodyColor: Color = defaultInnerBodyColor,
    private val outerBodyRadius: CornerRadius = defaultOuterBodyRadius,
    private val innerBodyRadius: CornerRadius = defaultInnerBodyRadius,
    private val innerBodyInsets: EdgeInsets = defaultInnerBodyInsets,
    private val screenInsets: EdgeInsets = defaultScreenInsets,
    private val screenRadius: CornerRadius = defaultScreenRadius,
    private val footSize: Size = defaultFootSize,
    private val footBarWidth: Float = DEFAULT_FOOT_BAR_WIDTH,
    private val footBaseHeight: Float = DEFAULT_FOOT_BASE_HEIGHT,
    private val windowRadius: CornerRadius = defaultWindowRadius
) : CustomPainter {

    /**
     * Calculates the total frame size (monitor body + screen + foot).
     *
     * @param screenSize The size of the inner screen content.
     * @return The complete frame size including padding and foot.
     */
    override fun calculateFrameSize(screenSize: Size): Size {
        return Size(
            width = screenSize.width + innerBodyInsets.horizontal + screenInsets.horizontal,
            height = screenSize.height + innerBodyInsets.vertical + screenInsets.vertical + footSize.height
        )
    }

    /**
     * Returns the effective size of the floating window (excluding the title bar).
     */
    val effectiveWindowSize: Size
        get() = Size(
            width = windowPosition.width,
            height = windowPosition.height - barHeight
        )

    /**
     * Determines the height of the window title bar depending on the target platform.
     */
    private val barHeight: Float
        get() = when (platform) {
            TargetPlatform.MACOS -> 30f
            else -> 40f
        }

    /**
     * Computes the absolute position of the floating window within the monitor screen.
     */
    private val windowLocation: Offset
        get() = Offset(
            x = innerBodyInsets.left + screenInsets.left + windowPosition.left,
            y = innerBodyInsets.top + screenInsets.top + windowPosition.top
        )

    /**
     * Creates a path for the floating window with rounded corners.
     *
     * @param screenSize The screen size within the monitor body.
     * @return A [Path] describing the floating window.
     */
    override fun createScreenPath(screenSize: Size): Path {
        return Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(
                        offset = windowLocation + Offset(0f, barHeight),
                        size = effectiveWindowSize
                    ),
                    cornerRadius = windowRadius
                )
            )
        }
    }

    /**
     * Main drawing function that triggers the monitor component rendering.
     */
    override fun draw(drawScope: DrawScope) {
        with(drawScope) {
            drawMonitorComponents()
        }
    }

    /**
     * Draws the complete monitor, including the foot, body, screen, window, and window bar.
     */
    private fun DrawScope.drawMonitorComponents() {
        val paint = Paint().apply { style = PaintingStyle.Fill }
        val bounds = Rect(
            left = 0f,
            top = 0f,
            right = size.width,
            bottom = size.height - footSize.height
        )

        drawFootStand(bounds, paint)
        drawMonitorBody(bounds, paint)
        drawScreenContent(bounds)
        drawWindowWithShadow()
        drawWindowBar()
    }

    /**
     * Draws the monitor's foot including the central bar and base.
     */
    private fun DrawScope.drawFootStand(bounds: Rect, paint: Paint) {
        val footCenter = bounds.bottomCenter + Offset(0f, footSize.height * 0.5f)
        val footBounds = Rect(
            left = footCenter.x - footSize.width / 2f,
            top = footCenter.y - footSize.height / 2f,
            right = footCenter.x + footSize.width / 2f,
            bottom = footCenter.y + footSize.height / 2f
        )

        drawMonitorFoot(
            paint = paint,
            bounds = footBounds,
            innerBodyColor = innerBodyColor,
            innerBodyRadius = innerBodyRadius,
            outerBodyColor = outerBodyColor,
            outerBodyRadius = outerBodyRadius,
            baseHeight = footBaseHeight,
            bottomHeight = 6f,
            baseTopRadius = CornerRadius(footBaseHeight * 0.30f),
            baseBottomRadius = CornerRadius(footBaseHeight * 0.1f),
            footBarSpace = footBarWidth * 0.6f,
            footBarWidth = footBarWidth
        )
    }

    /**
     * Draws the outer and inner monitor frames.
     */
    private fun DrawScope.drawMonitorBody(bounds: Rect, paint: Paint) {
        drawDeviceBody(
            paint = paint,
            bounds = bounds,
            innerBodyColor = innerBodyColor,
            innerBodyInsets = innerBodyInsets,
            innerBodyRadius = innerBodyRadius,
            outerBodyColor = outerBodyColor,
            outerBodyRadius = outerBodyRadius
        )
    }

    /**
     * Draws the inner screen area and its content (wallpaper).
     */
    private fun DrawScope.drawScreenContent(bounds: Rect) {
        val screenBounds = Rect(
            left = innerBodyInsets.left + screenInsets.left,
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
    }

    /**
     * Draws the floating window's shadow to give it depth and separation.
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
     * Draws the window's title bar at the top.
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
        // Default styling constants

        val defaultOuterBodyColor = Color(0xff3A4245)
        val defaultOuterBodyRadius = CornerRadius(30f)

        val defaultInnerBodyColor = Color(0xff121515)
        val defaultInnerBodyRadius = CornerRadius(20f)

        val defaultScreenRadius = CornerRadius(10f)
        val defaultWindowRadius = CornerRadius(6f)

        val defaultFootSize = Size(920f, 280f)
        const val DEFAULT_FOOT_BASE_HEIGHT = 40f
        const val DEFAULT_FOOT_BAR_WIDTH = 60f

        val defaultInnerBodyInsets = EdgeInsets(
            left = 6f,
            top = 6f,
            right = 6f,
            bottom = 6f
        )

        val defaultScreenInsets = EdgeInsets(
            left = 20f,
            top = 20f,
            right = 20f,
            bottom = 40f
        )
    }
}
