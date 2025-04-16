package io.github.estivensh.kframe.info

/**
 * Represents the padding or margins on each edge of a rectangle (left, top, right, bottom).
 *
 * @param left The padding or margin on the left side.
 * @param top The padding or margin on the top side.
 * @param right The padding or margin on the right side.
 * @param bottom The padding or margin on the bottom side.
 */
data class EdgeInsets(
    val left: Float = 0f,
    val top: Float = 0f,
    val right: Float = 0f,
    val bottom: Float = 0f
) {
    /**
     * Calculates the total horizontal padding or margin (left + right).
     */
    val horizontal: Float get() = left + right

    /**
     * Calculates the total vertical padding or margin (top + bottom).
     */
    val vertical: Float get() = top + bottom

    companion object {
        /**
         * A constant representing zero padding or margin on all edges.
         */
        val Zero = EdgeInsets()
    }
}