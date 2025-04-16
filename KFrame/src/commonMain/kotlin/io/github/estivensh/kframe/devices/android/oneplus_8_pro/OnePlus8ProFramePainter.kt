package io.github.estivensh.kframe.devices.android.oneplus_8_pro

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import io.github.estivensh.kframe.devices.generic.CustomPainter

class OnePlus8ProFramePainter : CustomPainter {

    private companion object {
        val FrameColor = Color(0xFF3A4245)
        val InnerFrameColor = Color(0xFF121515)
        val CameraOuterColor = Color(0xFF262C2D)
        val CameraInnerColor = Color(0xFF121515)
        val CameraLensColor = Color(0xFF636F73)
        val TopBarColor = Color(0xFF262C2D)

        val FrameSize = Size(852f, 1865f)
        val ScreenRect = Rect(
            left = 10.87f,
            top = 142.36f,
            right = 841.13f,
            bottom = 1722.47f
        )
        val ScreenCornerRadius = CornerRadius(20f)
    }

    // Region: CustomPainter Implementation
    override fun calculateFrameSize(screenSize: Size): Size = FrameSize

    override fun createScreenPath(screenSize: Size): Path = Path().apply {
        addRoundRect(RoundRect(rect = ScreenRect, cornerRadius = ScreenCornerRadius))
    }

    // Region: Drawing Implementation
    override fun draw(drawScope: DrawScope) = with(drawScope) {
        drawDeviceFrame()
        drawInnerFrame()
        drawCameraSystem()
        drawTopBar()
    }

    private fun DrawScope.drawDeviceFrame() {
        drawPath(
            path = Path().apply {
                moveTo(6.52f, 147.8f)
                cubicTo(6.52f, 90.88f, 6.52f, 62.42f, 19.33f, 41.51f)
                cubicTo(26.5f, 29.82f, 36.34f, 19.98f, 48.03f, 12.81f)
                cubicTo(68.94f, 0f, 97.4f, 0f, 154.32f, 0f)
                lineTo(697.68f, 0f)
                cubicTo(754.6f, 0f, 783.06f, 0f, 803.97f, 12.81f)
                cubicTo(815.66f, 19.98f, 825.5f, 29.82f, 832.67f, 41.51f)
                cubicTo(845.48f, 62.42f, 845.48f, 90.88f, 845.48f, 147.8f)
                lineTo(845.48f, 1717.04f)
                cubicTo(845.48f, 1773.96f, 845.48f, 1802.42f, 832.67f, 1823.32f)
                cubicTo(825.5f, 1835.02f, 815.66f, 1844.86f, 803.97f, 1852.03f)
                cubicTo(783.06f, 1864.84f, 754.6f, 1864.84f, 697.68f, 1864.84f)
                lineTo(154.32f, 1864.84f)
                cubicTo(97.4f, 1864.84f, 68.94f, 1864.84f, 48.03f, 1852.03f)
                cubicTo(36.34f, 1844.86f, 26.5f, 1835.02f, 19.33f, 1823.32f)
                cubicTo(6.52f, 1802.42f, 6.52f, 1773.96f, 6.52f, 1717.04f)
                close()
            },
            color = FrameColor,
            style = Fill
        )
    }

    private fun DrawScope.drawInnerFrame() {
        drawPath(
            path = Path().apply {
                moveTo(10.87f, 142.36f)
                cubicTo(10.87f, 92.56f, 10.87f, 67.66f, 22.08f, 49.37f)
                cubicTo(28.35f, 39.13f, 36.96f, 30.52f, 47.19f, 24.25f)
                cubicTo(65.48f, 13.04f, 90.39f, 13.04f, 140.19f, 13.04f)
                lineTo(711.81f, 13.04f)
                cubicTo(761.61f, 13.04f, 786.52f, 13.04f, 804.81f, 24.25f)
                cubicTo(815.04f, 30.52f, 823.65f, 39.13f, 829.92f, 49.37f)
                cubicTo(841.13f, 67.66f, 841.13f, 92.56f, 841.13f, 142.36f)
                lineTo(841.13f, 1722.47f)
                cubicTo(841.13f, 1772.28f, 841.13f, 1797.18f, 829.92f, 1815.47f)
                cubicTo(823.65f, 1825.71f, 815.04f, 1834.31f, 804.81f, 1840.59f)
                cubicTo(786.52f, 1851.8f, 761.61f, 1851.8f, 711.81f, 1851.8f)
                lineTo(140.19f, 1851.8f)
                cubicTo(90.39f, 1851.8f, 65.48f, 1851.8f, 47.19f, 1840.59f)
                cubicTo(36.96f, 1834.31f, 28.35f, 1825.71f, 22.08f, 1815.47f)
                cubicTo(10.87f, 1797.18f, 10.87f, 1772.28f, 10.87f, 1722.47f)
                close()
            },
            color = InnerFrameColor,
            style = Fill
        )
    }

    private fun DrawScope.drawCameraSystem() {
        // Outer camera ring
        drawPath(
            path = Path().apply { addOval(Rect(86.94f, 67.38f, 130.41f, 110.85f)) },
            color = CameraOuterColor,
            style = Fill
        )

        // Inner camera circle
        drawPath(
            path = Path().apply { addOval(Rect(95.09f, 75.53f, 122.26f, 102.7f)) },
            color = CameraInnerColor,
            style = Fill
        )

        // Camera lens
        drawPath(
            path = Path().apply { addOval(Rect(105.96f, 80.96f, 111.39f, 86.4f)) },
            color = CameraLensColor,
            style = Fill
        )
    }

    private fun DrawScope.drawTopBar() {
        drawPath(
            path = Path().apply {
                moveTo(319.5f, 26.08f)
                cubicTo(315.53f, 26.08f, 315.19f, 20.64f, 311.85f, 19.7f)
                cubicTo(311.47f, 19.59f, 311.3f, 19.11f, 311.6f, 18.88f)
                cubicTo(312.43f, 18.24f, 313.79f, 17.39f, 315.15f, 17.39f)
                lineTo(536.85f, 17.39f)
                cubicTo(538.21f, 17.39f, 539.57f, 18.24f, 540.4f, 18.88f)
                cubicTo(540.7f, 19.11f, 540.53f, 19.59f, 540.15f, 19.7f)
                cubicTo(536.81f, 20.64f, 536.47f, 26.08f, 532.5f, 26.08f)
                close()
            },
            color = TopBarColor,
            style = Fill
        )
    }
}