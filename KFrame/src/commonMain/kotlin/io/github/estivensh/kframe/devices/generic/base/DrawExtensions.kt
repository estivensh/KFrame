package io.github.estivensh.kframe.devices.generic.base

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import io.github.estivensh.kframe.info.EdgeInsets
import io.github.estivensh.kframe.info.SideButtonSide
import io.github.estivensh.kframe.info.TargetPlatform


fun DrawScope.drawDeviceBody(
    paint: Paint,
    bounds: Rect,
    outerBodyColor: Color,
    outerBodyRadius: CornerRadius,
    innerBodyColor: Color,
    innerBodyRadius: CornerRadius,
    innerBodyInsets: EdgeInsets
) {
    // Draw outer body
    drawRoundRect(
        color = outerBodyColor,
        topLeft = bounds.topLeft,
        size = bounds.size,
        cornerRadius = outerBodyRadius,
        style = if (paint.style == PaintingStyle.Fill) Fill else Stroke(width = paint.strokeWidth),
        alpha = paint.alpha,
        colorFilter = paint.colorFilter,
        blendMode = paint.blendMode
    )

    // Calculate inner bounds
    val innerBounds = Rect(
        left = bounds.left + innerBodyInsets.left,
        top = bounds.top + innerBodyInsets.top,
        right = bounds.right - innerBodyInsets.right,
        bottom = bounds.bottom - innerBodyInsets.bottom
    )

    // Draw inner body
    drawRoundRect(
        color = innerBodyColor,
        topLeft = innerBounds.topLeft,
        size = innerBounds.size,
        cornerRadius = innerBodyRadius,
        style = if (paint.style == PaintingStyle.Fill) Fill else Stroke(width = paint.strokeWidth),
        alpha = paint.alpha,
        colorFilter = paint.colorFilter,
        blendMode = paint.blendMode
    )
}

fun DrawScope.drawCamera(
    paint: Paint,
    center: Offset,
    radius: Float,
    borderWidth: Float,
    borderColor: Color,
    innerColor: Color,
    reflectColor: Color
) {
    // Draw border circle
    drawCircle(
        color = borderColor,
        center = center,
        radius = radius + borderWidth,
        style = if (paint.style == PaintingStyle.Fill) Fill else Stroke(width = paint.strokeWidth),
        alpha = paint.alpha,
        colorFilter = paint.colorFilter,
        blendMode = paint.blendMode
    )

    // Draw main camera circle
    drawCircle(
        color = innerColor,
        center = center,
        radius = radius,
        style = if (paint.style == PaintingStyle.Fill) Fill else Stroke(width = paint.strokeWidth),
        alpha = paint.alpha,
        colorFilter = paint.colorFilter,
        blendMode = paint.blendMode
    )

    // Draw reflection highlight
    drawCircle(
        color = reflectColor,
        center = center - Offset(0f, radius * 0.25f),
        radius = radius / 3f,
        style = if (paint.style == PaintingStyle.Fill) Fill else Stroke(width = paint.strokeWidth),
        alpha = paint.alpha,
        colorFilter = paint.colorFilter,
        blendMode = paint.blendMode
    )
}

fun DrawScope.drawSideButtons(
    paint: Paint,
    bounds: Rect,
    color: Color,
    buttonWidth: Float,
    side: SideButtonSide,
    inverted: Boolean,
    gapsAndSizes: List<Float>
) {
    if (gapsAndSizes.size > 1) {
        var x = 0f
        var y = 0f

        when (side) {
            SideButtonSide.LEFT -> {
                x = bounds.left
                y = bounds.top
            }

            SideButtonSide.RIGHT -> {
                x = bounds.right
                y = bounds.top
            }

            SideButtonSide.TOP -> {
                y = bounds.top
                x = bounds.left
            }

            SideButtonSide.BOTTOM -> {
                y = bounds.bottom
                x = bounds.left
            }
        }

        val buttonRadius = CornerRadius(buttonWidth)
        for (i in gapsAndSizes.indices) {
            val current = gapsAndSizes[i]
            val isGap = (i % 2) == 0
            if (!isGap) {
                val rect = when (side) {
                    SideButtonSide.LEFT, SideButtonSide.RIGHT -> Rect(
                        left = x - buttonWidth,
                        top = if (inverted) bounds.bottom - y - current else y,
                        right = x + buttonWidth,
                        bottom = if (inverted) bounds.bottom - y else y + current
                    )

                    SideButtonSide.TOP, SideButtonSide.BOTTOM -> Rect(
                        left = if (inverted) bounds.right - x - current else x,
                        top = y - buttonWidth,
                        right = if (inverted) bounds.right - x else x + current,
                        bottom = y + buttonWidth
                    )
                }

                drawRoundRect(
                    color = color,
                    topLeft = rect.topLeft,
                    size = Size(rect.width, rect.height),
                    cornerRadius = buttonRadius,
                    style = if (paint.style == PaintingStyle.Fill) Fill else Stroke(width = paint.strokeWidth),
                    alpha = paint.alpha,
                    colorFilter = paint.colorFilter,
                    blendMode = paint.blendMode
                )
            }

            when (side) {
                SideButtonSide.LEFT, SideButtonSide.RIGHT -> y += current
                SideButtonSide.TOP, SideButtonSide.BOTTOM -> x += current
            }
        }
    }
}

fun DrawScope.drawDefaultWallpaper(
    platform: TargetPlatform,
    bounds: Rect
) {
    val colors = when (platform) {
        TargetPlatform.MACOS -> listOf(
            Color(0xFF4817A6),
            Color(0xFFB236AF),
            Color(0xFFC7BDD6),
            Color(0xFFC870A2)
        )

        TargetPlatform.LINUX -> listOf(
            Color(0xFFBE15DA),
            Color(0xFFE22888),
            Color(0xFFD84E00)
        )

        else -> listOf(
            Color(0xFF1491F8),
            Color(0xFF0043D8)
        )
    }

    drawRect(
        brush = Brush.radialGradient(
            colors = colors,
            center = Offset(0f, bounds.height),
            radius = bounds.width
        ),
        topLeft = bounds.topLeft,
        size = bounds.size
    )
}

fun DrawScope.drawWindowBar(
    platform: TargetPlatform,
    bounds: Rect,
    windowRadius: CornerRadius
) {
    val paint = Paint().apply {
        style = PaintingStyle.Fill
        color = Color.White
    }

    when (platform) {
        TargetPlatform.LINUX -> {
            // Draw window background
            drawRoundRect(
                color = Color(0xFE323030),
                topLeft = bounds.topLeft,
                size = bounds.size,
                cornerRadius = CornerRadius(
                    x = windowRadius.x,
                    y = windowRadius.x
                ),
                style = Fill
            )

            val iconSize = Size(22f, 22f)
            val innerIconSize = Size(8f, 8f)

            // Close button (red circle with X)
            var iconBounds = Rect(
                left = bounds.right - iconSize.width * 1.5f - iconSize.width,
                top = bounds.center.y - iconSize.height / 2,
                right = bounds.right - iconSize.width * 1.5f,
                bottom = bounds.center.y + iconSize.height / 2
            )
            var innerBounds = Rect(
                left = iconBounds.center.x - innerIconSize.width / 2,
                top = iconBounds.center.y - innerIconSize.height / 2 - 1f,
                right = iconBounds.center.x + innerIconSize.width / 2,
                bottom = iconBounds.center.y + innerIconSize.height / 2 - 1f
            )

            // Red circle
            drawCircle(
                color = Color(0xFFE95420),
                center = iconBounds.center,
                radius = iconBounds.width * 0.5f,
                style = Fill
            )

            // X mark
            drawLine(
                color = Color.White,
                start = innerBounds.topLeft,
                end = innerBounds.bottomRight,
                strokeWidth = 2f,
                cap = StrokeCap.Butt,
                pathEffect = null,
                alpha = 1f,
                colorFilter = null,
                blendMode = BlendMode.SrcOver
            )
            drawLine(
                color = Color.White,
                start = innerBounds.topRight,
                end = innerBounds.bottomLeft,
                strokeWidth = 2f,
                cap = StrokeCap.Butt,
                pathEffect = null,
                alpha = 1f,
                colorFilter = null,
                blendMode = BlendMode.SrcOver
            )

            // Minimize button (square)
            iconBounds = Rect(
                left = iconBounds.left - iconSize.width * 2f,
                top = iconBounds.top,
                right = iconBounds.right - iconSize.width * 2f,
                bottom = iconBounds.bottom
            )
            innerBounds = Rect(
                left = iconBounds.center.x - innerIconSize.width / 2,
                top = iconBounds.center.y - innerIconSize.height / 2,
                right = iconBounds.center.x + innerIconSize.width / 2,
                bottom = iconBounds.center.y + innerIconSize.height / 2
            )
            drawRect(
                color = Color.White,
                topLeft = innerBounds.topLeft,
                size = innerBounds.size,
                style = Stroke(width = 2f)
            )

            // Maximize button (bar)
            iconBounds = Rect(
                left = iconBounds.left - iconSize.width * 2f,
                top = iconBounds.top,
                right = iconBounds.right - iconSize.width * 2f,
                bottom = iconBounds.bottom
            )
            drawLine(
                color = Color.White,
                start = Offset(innerBounds.left, innerBounds.bottom),
                end = Offset(innerBounds.right, innerBounds.bottom),
                strokeWidth = 2f,
                cap = StrokeCap.Butt,
                pathEffect = null,
                alpha = 1f,
                colorFilter = null,
                blendMode = BlendMode.SrcOver
            )
        }

        TargetPlatform.WINDOWS -> {
            // Draw window background
            drawRoundRect(
                color = Color(0xBB000000),
                topLeft = bounds.topLeft,
                size = bounds.size,
                cornerRadius = CornerRadius(
                    x = windowRadius.x,
                    y = windowRadius.x
                ),
                style = Fill
            )

            val iconSize = Size(12f, 12f)

            // Close button (X)
            var iconBounds = Rect(
                left = bounds.right - iconSize.width * 2.5f - iconSize.width,
                top = bounds.center.y - iconSize.height / 2,
                right = bounds.right - iconSize.width * 2.5f,
                bottom = bounds.center.y + iconSize.height / 2
            )
            drawLine(
                color = Color.White,
                start = iconBounds.topLeft,
                end = iconBounds.bottomRight,
                strokeWidth = 2f,
                cap = StrokeCap.Butt,
                pathEffect = null,
                alpha = 1f,
                colorFilter = null,
                blendMode = BlendMode.SrcOver
            )
            drawLine(
                color = Color.White,
                start = iconBounds.topRight,
                end = iconBounds.bottomLeft,
                strokeWidth = 2f,
                cap = StrokeCap.Butt,
                pathEffect = null,
                alpha = 1f,
                colorFilter = null,
                blendMode = BlendMode.SrcOver
            )

            // Minimize button (square)
            iconBounds = Rect(
                left = iconBounds.left - iconSize.width * 3f,
                top = iconBounds.top,
                right = iconBounds.right - iconSize.width * 3f,
                bottom = iconBounds.bottom
            )
            drawRect(
                color = Color.White,
                topLeft = iconBounds.topLeft,
                size = iconBounds.size,
                style = Stroke(width = 2f)
            )

            // Maximize button (bar)
            iconBounds = Rect(
                left = iconBounds.left - iconSize.width * 3f,
                top = iconBounds.top,
                right = iconBounds.right - iconSize.width * 3f,
                bottom = iconBounds.bottom
            )
            drawLine(
                color = Color.White,
                start = Offset(iconBounds.left, iconBounds.center.y),
                end = Offset(iconBounds.right, iconBounds.center.y),
                strokeWidth = 2f,
                cap = StrokeCap.Butt,
                pathEffect = null,
                alpha = 1f,
                colorFilter = null,
                blendMode = BlendMode.SrcOver
            )
        }

        TargetPlatform.MACOS -> {
            // Draw window background
            drawRoundRect(
                color = Color(0xBB000000),
                topLeft = bounds.topLeft,
                size = bounds.size,
                cornerRadius = CornerRadius(
                    x = windowRadius.x,
                    y = windowRadius.x
                ),
                style = Fill
            )

            val iconSize = Size(12f, 12f)
            val iconOffset = 8f

            // Close button (red)
            var iconBounds = Rect(
                left = bounds.left + iconOffset,
                top = bounds.top + iconOffset,
                right = bounds.left + iconOffset + iconSize.width,
                bottom = bounds.top + iconOffset + iconSize.height
            )
            drawCircle(
                color = Color(0xFFEE695E),
                center = iconBounds.center,
                radius = iconBounds.width * 0.5f,
                style = Fill
            )

            // Minimize button (yellow)
            iconBounds = Rect(
                left = iconBounds.right + iconOffset,
                top = iconBounds.top,
                right = iconBounds.right + iconOffset + iconSize.width,
                bottom = iconBounds.bottom
            )
            drawCircle(
                color = Color(0xFFF5BD4E),
                center = iconBounds.center,
                radius = iconBounds.width * 0.5f,
                style = Fill
            )

            // Maximize button (green)
            iconBounds = Rect(
                left = iconBounds.right + iconOffset,
                top = iconBounds.top,
                right = iconBounds.right + iconOffset + iconSize.width,
                bottom = iconBounds.bottom
            )
            drawCircle(
                color = Color(0xFF61C354),
                center = iconBounds.center,
                radius = iconBounds.width * 0.5f,
                style = Fill
            )
        }

        else -> {
            // Default case does nothing
        }
    }
}

fun DrawScope.drawMonitorFoot(
    paint: Paint,
    bounds: Rect,
    outerBodyColor: Color,
    outerBodyRadius: CornerRadius,
    innerBodyColor: Color,
    innerBodyRadius: CornerRadius,
    footBarWidth: Float,
    footBarSpace: Float,
    bottomHeight: Float,
    baseHeight: Float,
    baseTopRadius: CornerRadius,
    baseBottomRadius: CornerRadius
) {
    // Left bar
    drawRect(
        color = innerBodyColor,
        topLeft = Offset(
            x = bounds.center.x - (footBarSpace / 2f) - footBarWidth,
            y = bounds.top
        ),
        size = Size(footBarWidth, bounds.height - bottomHeight),
        style = Fill
    )

    // Right bar
    drawRect(
        color = innerBodyColor,
        topLeft = Offset(
            x = bounds.center.x + (footBarSpace / 2f),
            y = bounds.top
        ),
        size = Size(footBarWidth, bounds.height - bottomHeight),
        style = Fill
    )

    // Base top
    drawRoundRect(
        color = outerBodyColor,
        topLeft = Offset(
            x = bounds.left,
            y = bounds.bottom - bottomHeight - baseHeight
        ),
        size = Size(bounds.width, baseHeight * 0.5f),
        cornerRadius = CornerRadius(
            x = baseTopRadius.x,
            y = baseTopRadius.x
        ),
        style = Fill
    )

    // Base bottom
    drawRoundRect(
        color = innerBodyColor,
        topLeft = Offset(
            x = bounds.left,
            y = bounds.bottom - bottomHeight - (baseHeight * 0.5f)
        ),
        size = Size(bounds.width, baseHeight * 0.5f),
        cornerRadius = CornerRadius(
            x = baseBottomRadius.x,
            y = baseBottomRadius.x
        ),
        style = Fill
    )

    // Bottom pads
    val bottomWidth = bottomHeight * 5f
    val bottomRadius = CornerRadius(bottomHeight)
    drawRoundRect(
        color = innerBodyColor,
        topLeft = Offset(
            x = bounds.left + bottomWidth,
            y = bounds.bottom - bottomHeight
        ),
        size = Size(bottomWidth, bottomHeight),
        cornerRadius = CornerRadius(
            x = bottomRadius.x,
            y = bottomRadius.x
        ),
        style = Fill
    )

    drawRoundRect(
        color = innerBodyColor,
        topLeft = Offset(
            x = bounds.right - bottomWidth * 2f,
            y = bounds.bottom - bottomHeight
        ),
        size = Size(bottomWidth, bottomHeight),
        cornerRadius = CornerRadius(
            x = bottomRadius.x,
            y = bottomRadius.x
        ),
        style = Fill
    )
}