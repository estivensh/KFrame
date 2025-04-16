package io.github.estivensh.kframe.info

/**
 * Represents the type of a device.
 */
enum class DeviceType {
    /** An unknown device type. */
    UNKNOWN,

    /** A phone device. */
    PHONE,

    /** A tablet device. */
    TABLET,

    /** A TV device. */
    TV,

    /** A desktop device. */
    DESKTOP,

    /** A laptop device. */
    LAPTOP;

    companion object {
        /**
         * Converts a string to a corresponding [DeviceType].
         * If no matching device type is found, it returns [UNKNOWN].
         *
         * @param value The string representation of the device type.
         * @return The matching [DeviceType], or [UNKNOWN] if no match is found.
         */
        fun from(value: String): DeviceType {
            return entries.firstOrNull {
                it.name.equals(value, ignoreCase = true)
            } ?: UNKNOWN
        }
    }
}
