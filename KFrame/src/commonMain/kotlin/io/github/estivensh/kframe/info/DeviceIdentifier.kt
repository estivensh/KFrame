package io.github.estivensh.kframe.info

/**
 * Represents a unique identifier for a device in a multiplatform context.
 *
 * @property name The name of the device. This can be a user-friendly label or a unique identifier.
 * @property type The type of device, represented by the [DeviceType] enum
 * (e.g., phone, tablet, watch, etc.).
 * @property platform The target platform the device runs on, represented by the [TargetPlatform] enum
 * (e.g., Android, iOS, Web, Desktop, etc.).
 *
 * The [toString] method generates a string in the format: `"platform_type_name"`, all in lowercase.
 * For example: `"android_phone_pixel5"`.
 */
data class DeviceIdentifier(
    val name: String,
    val type: DeviceType,
    val platform: TargetPlatform
) {
    /**
     * Returns a lowercase string identifier in the format: `"platform_type_name"`.
     * This format is useful for generating unique keys or for logging purposes.
     */
    override fun toString(): String {
        val platformKey = platform.toString()
            .removePrefix("TargetPlatform.")
            .lowercase()
        val typeKey = type.toString()
            .removePrefix("DeviceType.")
            .lowercase()
        return "${platformKey}_${typeKey}_$name"
    }
}