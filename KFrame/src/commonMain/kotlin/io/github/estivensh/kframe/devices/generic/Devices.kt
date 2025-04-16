package io.github.estivensh.kframe.devices.generic

import io.github.estivensh.kframe.devices.android.AndroidDevices
import io.github.estivensh.kframe.devices.ios.IOSDevices
import io.github.estivensh.kframe.info.DeviceInfo

object Devices {
    // All iOS devices.
    val ios = IOSDevices

    /*
        // All macOS devices.
        val macOS: MacOSDevices = MacOSDevices()
    */
    // All Android devices.
    val android = AndroidDevices

    /* // All Windows devices.
     val windows: WindowsDevices = WindowsDevices()

     // All Linux devices.
     val linux: LinuxDevices = LinuxDevices()*/

    // All available devices.
    val all: List<DeviceInfo>
        get() = listOf(
             *ios.all.toTypedArray(),
            /*macOS.all().toTypedArray(),*/
            *android.all.toTypedArray(),
            /* *windows.all().toTypedArray(),
             *linux.all().toTypedArray()*/
        )
}