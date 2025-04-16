package io.github.estivensh.kframe.devices.ios.iphone_13

import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import io.github.estivensh.kframe.devices.generic.CustomPainter

class IPhone13FramePainter : CustomPainter {

    private companion object {
        // Colors
        val DarkFrameColor = Color(0xFF1C3343)
        val MediumFrameColor = Color(0xFF213744)
        val LightFrameColor = Color(0xFF8EADC1)
        val ScreenColor = Color(0xFF121515)
        val ButtonColor = Color(0xFF262C2D)
        val DetailColor = Color(0xFF36454C)
        val LensColor = Color(0xFF636F73)

        // Dimensions
        val FrameSize = Size(873f, 1771f)
        val ScreenRect = Rect(
            left = 45f,
            top = 129.973f,
            right = 828f,
            bottom = 1637.98f
        )
        val ScreenCornerRadius = CornerRadius(40f)
    }

    override fun calculateFrameSize(screenSize: Size): Size = FrameSize

    override fun createScreenPath(screenSize: Size): Path = Path().apply {
        addRoundRect(
            RoundRect(
                rect = ScreenRect,
                cornerRadius = ScreenCornerRadius
            )
        )
    }

    override fun draw(drawScope: DrawScope) = with(drawScope) {
        drawDeviceFrame()
        drawInnerDetails()
        drawScreenArea()
        drawSideButtons()
        drawCameraSystem()
        drawTopBar()
        drawSmallDetails()
    }

    private fun DrawScope.drawDeviceFrame() {
        // Main device frame (Original Path 4)
        drawPath(
            Path().apply {
                moveTo(6.19141f, 187.809f)
                cubicTo(6.19141f, 137.871f, 6.19141f, 112.902f, 12.7571f, 92.6946f)
                cubicTo(26.0269f, 51.8546f, 58.046f, 19.8354f, 98.886f, 6.56572f)
                cubicTo(119.093f, 0f, 144.062f, 0f, 194f, 0f)
                lineTo(679f, 0f)
                cubicTo(728.938f, 0f, 753.907f, 0f, 774.114f, 6.56572f)
                cubicTo(814.954f, 19.8354f, 846.973f, 51.8546f, 860.243f, 92.6946f)
                cubicTo(866.808f, 112.902f, 866.808f, 137.871f, 866.808f, 187.809f)
                lineTo(866.808f, 1582.96f)
                cubicTo(866.808f, 1632.9f, 866.808f, 1657.86f, 860.243f, 1678.07f)
                cubicTo(846.973f, 1718.91f, 814.954f, 1750.93f, 774.114f, 1764.2f)
                cubicTo(753.907f, 1770.77f, 728.938f, 1770.77f, 679f, 1770.77f)
                lineTo(194f, 1770.77f)
                cubicTo(144.062f, 1770.77f, 119.093f, 1770.77f, 98.886f, 1764.2f)
                cubicTo(58.046f, 1750.93f, 26.0269f, 1718.91f, 12.7571f, 1678.07f)
                cubicTo(6.19141f, 1657.86f, 6.19141f, 1632.9f, 6.19141f, 1582.96f)
                lineTo(6.19141f, 187.809f)
                close()
            },
            color = MediumFrameColor,
            style = Fill
        )
    }

    private fun DrawScope.drawInnerDetails() {
        // Inner frame details (Original Path 5)
        drawPath(
            Path().apply {
                moveTo(679.825f, 4.12755f)
                lineTo(193.174f, 4.12755f)
                cubicTo(143.844f, 4.12755f, 119.668f, 4.15301f, 100.161f, 10.4912f)
                cubicTo(60.5778f, 23.3527f, 29.5438f, 54.3866f, 16.6824f, 93.97f)
                cubicTo(10.3442f, 113.477f, 10.3187f, 137.653f, 10.3187f, 186.983f)
                lineTo(10.3187f, 1583.78f)
                cubicTo(10.3187f, 1633.11f, 10.3442f, 1657.29f, 16.6824f, 1676.8f)
                cubicTo(29.5438f, 1716.38f, 60.5778f, 1747.41f, 100.161f, 1760.27f)
                cubicTo(119.668f, 1766.61f, 143.844f, 1766.64f, 193.174f, 1766.64f)
                lineTo(679.825f, 1766.64f)
                cubicTo(729.155f, 1766.64f, 753.331f, 1766.61f, 772.838f, 1760.27f)
                cubicTo(812.421f, 1747.41f, 843.455f, 1716.38f, 856.317f, 1676.8f)
                cubicTo(862.655f, 1657.29f, 862.68f, 1633.11f, 862.68f, 1583.78f)
                lineTo(862.68f, 186.983f)
                cubicTo(862.68f, 137.653f, 862.655f, 113.477f, 856.317f, 93.97f)
                cubicTo(843.455f, 54.3866f, 812.421f, 23.3527f, 772.838f, 10.4912f)
                cubicTo(753.331f, 4.15301f, 729.155f, 4.12755f, 679.825f, 4.12755f)
                close()
                moveTo(14.7196f, 93.3323f)
                cubicTo(8.25488f, 113.229f, 8.25488f, 137.813f, 8.25488f, 186.983f)
                lineTo(8.25488f, 1583.78f)
                cubicTo(8.25488f, 1632.95f, 8.25488f, 1657.54f, 14.7196f, 1677.43f)
                cubicTo(27.7852f, 1717.65f, 59.3117f, 1749.17f, 99.5235f, 1762.24f)
                cubicTo(119.42f, 1768.7f, 144.005f, 1768.7f, 193.174f, 1768.7f)
                lineTo(679.825f, 1768.7f)
                cubicTo(728.995f, 1768.7f, 753.579f, 1768.7f, 773.476f, 1762.24f)
                cubicTo(813.687f, 1749.17f, 845.214f, 1717.65f, 858.28f, 1677.43f)
                cubicTo(864.744f, 1657.54f, 864.744f, 1632.95f, 864.744f, 1583.78f)
                lineTo(864.744f, 186.983f)
                cubicTo(864.744f, 137.813f, 864.744f, 113.229f, 858.28f, 93.3323f)
                cubicTo(845.214f, 53.1206f, 813.687f, 21.594f, 773.476f, 8.52843f)
                cubicTo(753.579f, 2.06372f, 728.995f, 2.06372f, 679.825f, 2.06372f)
                lineTo(193.174f, 2.06372f)
                cubicTo(144.005f, 2.06372f, 119.42f, 2.06372f, 99.5235f, 8.52843f)
                cubicTo(59.3117f, 21.594f, 27.7852f, 53.1206f, 14.7196f, 93.3323f)
                close()
            },
            color = LightFrameColor,
            style = Fill
        )
    }

    private fun DrawScope.drawScreenArea() {
        // Screen area (Original Path 6)
        drawPath(
            Path().apply {
                moveTo(16.5107f, 183.681f)
                cubicTo(16.5107f, 137.584f, 16.5107f, 114.536f, 22.5714f, 95.8834f)
                cubicTo(34.8204f, 58.1849f, 64.3765f, 28.6287f, 102.075f, 16.3798f)
                cubicTo(120.728f, 10.3191f, 143.776f, 10.3191f, 189.872f, 10.3191f)
                lineTo(683.128f, 10.3191f)
                cubicTo(729.224f, 10.3191f, 752.272f, 10.3191f, 770.925f, 16.3798f)
                cubicTo(808.624f, 28.6287f, 838.18f, 58.1849f, 850.429f, 95.8834f)
                cubicTo(856.49f, 114.536f, 856.49f, 137.584f, 856.49f, 183.681f)
                lineTo(856.49f, 1587.09f)
                cubicTo(856.49f, 1633.18f, 856.49f, 1656.23f, 850.429f, 1674.88f)
                cubicTo(838.18f, 1712.58f, 808.624f, 1742.14f, 770.925f, 1754.39f)
                cubicTo(752.272f, 1760.45f, 729.224f, 1760.45f, 683.128f, 1760.45f)
                lineTo(189.872f, 1760.45f)
                cubicTo(143.776f, 1760.45f, 120.728f, 1760.45f, 102.075f, 1754.39f)
                cubicTo(64.3765f, 1742.14f, 34.8204f, 1712.58f, 22.5714f, 1674.88f)
                cubicTo(16.5107f, 1656.23f, 16.5107f, 1633.18f, 16.5107f, 1587.09f)
                lineTo(16.5107f, 183.681f)
                close()
            },
            color = ScreenColor,
            style = Fill
        )
    }

    private fun DrawScope.drawSideButtons() {
        // Right side button (Original Path 0)
        drawPath(
            Path().apply {
                moveTo(866.809f, 454.042f)
                lineTo(869.904f, 454.042f)
                cubicTo(871.614f, 454.042f, 873f, 455.428f, 873f, 457.138f)
                lineTo(873f, 659.394f)
                cubicTo(873f, 661.103f, 871.614f, 662.489f, 869.904f, 662.489f)
                lineTo(866.809f, 662.489f)
                lineTo(866.809f, 454.042f)
                close()
            },
            color = DarkFrameColor,
            style = Fill
        )

        // Left side top button (Original Path 1)
        drawPath(
            Path().apply {
                moveTo(6.19141f, 705.83f)
                lineTo(3.09565f, 705.83f)
                cubicTo(1.38592f, 705.83f, 0f, 704.444f, 0f, 702.734f)
                lineTo(0f, 580.968f)
                cubicTo(0f, 579.258f, 1.38593f, 577.872f, 3.09566f, 577.872f)
                lineTo(6.19142f, 577.872f)
                lineTo(6.19141f, 705.83f)
                close()
            },
            color = DarkFrameColor,
            style = Fill
        )

        // Left side middle button (Original Path 2)
        drawPath(
            Path().apply {
                moveTo(6.19141f, 536.596f)
                lineTo(3.09565f, 536.596f)
                cubicTo(1.38592f, 536.596f, 0f, 535.21f, 0f, 533.5f)
                lineTo(0f, 411.734f)
                cubicTo(0f, 410.024f, 1.38593f, 408.638f, 3.09566f, 408.638f)
                lineTo(6.19142f, 408.638f)
                lineTo(6.19141f, 536.596f)
                close()
            },
            color = MediumFrameColor,
            style = Fill
        )

        // Left side bottom button (Original Path 3)
        drawPath(
            Path().apply {
                moveTo(6.19141f, 346.723f)
                lineTo(3.09566f, 346.723f)
                cubicTo(1.38592f, 346.723f, 0f, 345.337f, 0f, 343.628f)
                lineTo(0f, 283.777f)
                cubicTo(0f, 282.067f, 1.38593f, 280.681f, 3.09566f, 280.681f)
                lineTo(6.19141f, 280.681f)
                lineTo(6.19141f, 346.723f)
                close()
            },
            color = MediumFrameColor,
            style = Fill
        )
    }

    private fun DrawScope.drawCameraSystem() {
        // Camera outer ring (Original Path 14)
        drawPath(
            Path().apply {
                moveTo(328.511f, 77.0213f)
                cubicTo(337.629f, 77.0213f, 345.021f, 69.6292f, 345.021f, 60.5106f)
                cubicTo(345.021f, 51.3921f, 337.629f, 44f, 328.511f, 44f)
                cubicTo(319.392f, 44f, 312f, 51.3921f, 312f, 60.5106f)
                cubicTo(312f, 69.6292f, 319.392f, 77.0213f, 328.511f, 77.0213f)
                close()
            },
            color = ButtonColor,
            style = Fill
        )

        // Camera inner circle (Original Path 15)
        drawPath(
            Path().apply {
                moveTo(328.511f, 70.8297f)
                cubicTo(334.21f, 70.8297f, 338.83f, 66.2097f, 338.83f, 60.5106f)
                cubicTo(338.83f, 54.8114f, 334.21f, 50.1914f, 328.511f, 50.1914f)
                cubicTo(322.811f, 50.1914f, 318.191f, 54.8114f, 318.191f, 60.5106f)
                cubicTo(318.191f, 66.2097f, 322.811f, 70.8297f, 328.511f, 70.8297f)
                close()
            },
            color = ScreenColor,
            style = Fill
        )

        // Camera lens (Original Path 16)
        drawPath(
            Path().apply {
                moveTo(328.511f, 58.4468f)
                cubicTo(329.651f, 58.4468f, 330.575f, 57.5227f, 330.575f, 56.3829f)
                cubicTo(330.575f, 55.2431f, 329.651f, 54.3191f, 328.511f, 54.3191f)
                cubicTo(327.371f, 54.3191f, 326.447f, 55.2431f, 326.447f, 56.3829f)
                cubicTo(326.447f, 57.5227f, 327.371f, 58.4468f, 328.511f, 58.4468f)
                close()
            },
            color = LensColor,
            style = Fill
        )
    }

    private fun DrawScope.drawTopBar() {
        // Top bar/notch area (Original Path 7)
        drawPath(
            Path().apply {
                moveTo(365f, 10f)
                lineTo(506f, 10f)
                lineTo(506f, 14f)
                cubicTo(506f, 18.4183f, 502.418f, 22f, 498f, 22f)
                lineTo(373f, 22f)
                cubicTo(368.582f, 22f, 365f, 18.4183f, 365f, 14f)
                lineTo(365f, 10f)
                close()
            },
            color = ButtonColor,
            style = Fill
        )
    }

    private fun DrawScope.drawSmallDetails() {
        // Small detail rectangles (Original Paths 8-13)
        drawRect(
            color = DetailColor,
            topLeft = Offset(size.width * 0.7825063f, 0f),
            size = Size(size.width * 0.01418442f, size.height * 0.005826708f)
        )

        drawRect(
            color = DetailColor,
            topLeft = Offset(size.width * 0.9810871f, size.height * 0.1002196f),
            size = Size(size.width * 0.01182027f, size.height * 0.006992095f)
        )

        drawRect(
            color = DetailColor,
            topLeft = Offset(size.width * 0.007092108f, size.height * 0.1002196f),
            size = Size(size.width * 0.01182027f, size.height * 0.006992095f)
        )

        drawRect(
            color = DetailColor,
            topLeft = Offset(size.width * 0.007092108f, size.height * 0.8926539f),
            size = Size(size.width * 0.01182027f, size.height * 0.006992095f)
        )

        drawRect(
            color = DetailColor,
            topLeft = Offset(size.width * 0.9810871f, size.height * 0.8926539f),
            size = Size(size.width * 0.01182027f, size.height * 0.006992095f)
        )

        drawRect(
            color = DetailColor,
            topLeft = Offset(size.width * 0.2033093f, size.height * 0.9940429f),
            size = Size(size.width * 0.01418442f, size.height * 0.005826708f)
        )
    }
}