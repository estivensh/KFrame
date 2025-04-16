package io.github.estivensh.kframe.devices.ios

import io.github.estivensh.kframe.devices.ios.iphone_13.IPhone13
import io.github.estivensh.kframe.info.DeviceInfo

object IOSDevices {
    // Phone devices
    val iPhone13: DeviceInfo = IPhone13.create()

    // All devices list
    val all: List<DeviceInfo>
        get() = phones + tablets

    // Phones only
    val phones: List<DeviceInfo> = listOf(
        iPhone13,
    )

    // Tablets only
    val tablets: List<DeviceInfo> = listOf(

    )
}