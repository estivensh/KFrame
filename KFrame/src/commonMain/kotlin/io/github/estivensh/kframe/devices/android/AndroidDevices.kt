package io.github.estivensh.kframe.devices.android

import io.github.estivensh.kframe.devices.android.oneplus_8_pro.OnePlus8Pro
import io.github.estivensh.kframe.info.DeviceInfo

object AndroidDevices {

    val onePlus8Pro: DeviceInfo = OnePlus8Pro.create()

    val all: List<DeviceInfo>
        get() = phones + tablets

    val phones: List<DeviceInfo>
        get() = listOf(
            onePlus8Pro,
        )

    val tablets: List<DeviceInfo>
        get() = listOf(

        )
}