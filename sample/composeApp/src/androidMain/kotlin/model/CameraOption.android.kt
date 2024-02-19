package model

import state.CaptureMode

actual fun CameraOption.toCaptureMode(): CaptureMode = when (this) {
    CameraOption.Photo -> CaptureMode.Image
    CameraOption.Video -> CaptureMode.Video
}