package io.github.estivensh.kframe

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.estivensh.kframe.info.DeviceInfo
import io.github.estivensh.kframe.info.Orientation

/**
 * The actual screen content container with proper density and orientation handling.
 */
@Composable
fun DeviceScreen(
    device: DeviceInfo,
    orientation: Orientation,
    modifier: Modifier = Modifier,
    isFrameVisible: Boolean = true,
    content: @Composable () -> Unit
) {
    val screenPath = remember { device.screenPath }
    val bounds = remember { screenPath.getBounds() }
    val isRotated = remember(orientation) { device.isLandscape(orientation) }

    Box(
        modifier = modifier
            .then(
                if (isFrameVisible) Modifier.size(device.frameSize.width.dp, device.frameSize.height.dp)
                else Modifier.size(bounds.width.dp, bounds.height.dp)
            )
            .rotate(if (isRotated) -90f else 0f)
    ) {
        // 1. Dibujamos el frame y el path de referencia (DEBUG)
        if (isFrameVisible) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                device.framePainter.draw(this)
                // Path de referencia (se ve bien)
                //drawPath(screenPath, Color.Green.copy(alpha = 0.3f), style = Stroke(4f))
            }
        }

        // 2. Contenedor de contenido con clip PERFECTO
        Box(
            modifier = Modifier
                .matchParentSize()
                .drawWithContent {
                    // Clip que usa EXACTAMENTE las mismas coordenadas que el path visible
                    clipPath(screenPath, clipOp = ClipOp.Intersect) {
                        this@drawWithContent.drawContent()
                    }
                }
                .graphicsLayer {
                    clip = true
                    shape = object : Shape {
                        override fun createOutline(
                            size: Size,
                            layoutDirection: LayoutDirection,
                            density: Density
                        ): Outline {
                            return Outline.Generic(screenPath)
                        }
                    }
                }
        ) {
            // 3. Ajuste fino de posición del contenido
            Box(
                modifier = Modifier
                    .offset(
                        x = (-bounds.left).dp,
                        y = (-bounds.top).dp
                    )
                    .size(bounds.width.dp, bounds.height.dp)
            ) {
                content()
            }
        }
    }
}