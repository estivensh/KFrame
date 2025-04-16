package io.github.estivensh.kframe.devices.ios.iphone_13

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType

fun iPhone13ScreenPath(): Path {
    return Path().apply {
        moveTo(45.1305f, 129.973f)
        cubicTo(
            45.0439f, 131.645f,
            45f, 133.329f,
            45f, 135.022f
        )
        lineTo(45f, 1637.98f)
        cubicTo(
            45f, 1691.01f,
            88.002f, 1734f,
            141.048f, 1734f
        )
        lineTo(731.952f, 1734f)
        cubicTo(
            784.998f, 1734f,
            828f, 1691.01f,
            828f, 1637.98f
        )
        lineTo(828f, 135.022f)
        cubicTo(
            828f, 134.815f,
            827.999f, 134.608f,
            827.998f, 134.401f
        )
        cubicTo(
            827.664f, 81.6555f,
            784.791f, 39f,
            731.952f, 39f
        )
        lineTo(596.761f, 39f)
        cubicTo(
            589.566f, 41.5313f,
            584.408f, 48.3863f,
            584.408f, 56.4451f
        )
        cubicTo(
            584.408f, 81.9729f,
            563.708f, 102.667f,
            538.174f, 102.667f
        )
        lineTo(332.826f, 102.667f)
        cubicTo(
            307.292f, 102.667f,
            286.592f, 81.9729f,
            286.592f, 56.4451f
        )
        cubicTo(
            286.592f, 48.3863f,
            281.434f, 41.5313f,
            274.239f, 39f
        )
        lineTo(141.048f, 39f)
        cubicTo(
            117.114f, 39f,
            95.2253f, 47.7516f,
            78.408f, 62.2285f
        )
        cubicTo(
            71.9295f, 67.8055f,
            66.2036f, 74.2321f,
            61.4035f, 81.3353f
        )
        cubicTo(
            51.9291f, 95.3554f,
            46.0612f, 112.011f,
            45.1305f, 129.973f
        )
        close()

        fillType = PathFillType.EvenOdd
    }
}