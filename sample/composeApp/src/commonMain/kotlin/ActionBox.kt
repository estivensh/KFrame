import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import extensions.ImageFile
import model.CameraOption

@Composable
fun ActionBox(
    modifier: Modifier = Modifier,
    cameraOption: CameraOption,
    isRecording: Boolean,
    qrCodeText: String?,
    lastPicture: ImageFile?,
    isVideoSupported: Boolean,
    onGalleryClick: () -> Unit,
    onTakePicture: () -> Unit,
    onSwitchCamera: () -> Unit,
    onRecording: () -> Unit,
    onCameraOptionChanged: (CameraOption) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        QrCodeBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            qrCodeText = qrCodeText
        )
        OptionSection(
            modifier = Modifier.fillMaxWidth(),
            isVideoSupported = isVideoSupported,
            currentCameraOption = cameraOption,
            onCameraOptionChanged = onCameraOptionChanged
        )
        PictureActions(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 32.dp),
            isVideo = cameraOption == CameraOption.Video,
            lastPicture = lastPicture,
            isRecording = isRecording,
            onGalleryClick = onGalleryClick,
            onRecording = onRecording,
            onTakePicture = onTakePicture,
            onSwitchCamera = onSwitchCamera
        )
    }
}