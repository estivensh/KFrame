# 📱 KFrame

**KFrame** is a Kotlin Multiplatform library that lets you preview your UI inside simulated device frames—just like using an emulator, but directly inside your app or design tool. It supports Android, iOS, Desktop, and Web platforms, helping you visualize and refine your UI across multiple devices in one place.

---

## ✨ Features

- 🔁 **Cross-platform preview** — Android, iOS, Desktop, and Web.
- 🖼️ **Device frame simulation** — Wrap your UI in realistic device outlines.
- 📐 **Custom safe areas** — Simulate different device notches and paddings.
- 🎨 **Custom painter support** — Define your own device frames easily.
- 🧪 Perfect for design validation and UI previews in development tools.

---

## 📦 Installation

> Coming soon: Gradle setup instructions when published to Maven Central / GitHub Packages.

---

## 🧰 Usage

```kotlin
val device = buildGenericPhoneDevice(
    platform = TargetPlatform.ANDROID,
    id = "oneplus_8",
    name = "OnePlus 8",
    screenSize = Size(1080f, 2400f)
)

KFramePreview(device = device) {
    // Your composable content here
}
```

---

## 📱 Available Devices

| Device ID     | Name        | Type   | Platform |
|---------------|-------------|--------|----------|
| `oneplus_8`   | OnePlus 8   | Phone  | Android  |
| `iphone_13`   | iPhone 13   | Phone  | iOS      |

More coming soon!

---

## 🧩 Create Custom Device

You can also create your own device by using `CustomPainter`:

```kotlin
val myCustomDevice = DefaultDeviceInfo(
    identifier = DeviceIdentifier(
        platform = TargetPlatform.DESKTOP,
        type = DeviceType.DESKTOP,
        name = "my_custom_desktop"
    ),
    name = "My Desktop",
    screenSize = Size(1440f, 900f),
    screenPath = myCustomPainter.createScreenPath(screenSize),
    pixelRatio = 1.0f,
    safeAreas = EdgeInsets.Zero,
    framePainter = myCustomPainter,
    frameSize = myCustomPainter.calculateFrameSize(screenSize)
)
```

---

## 🛠️ Contributing

Contributions are welcome! Feel free to open issues, submit PRs, or suggest features. Let's build a device library for everyone 🚀

---

## 🧾 License

MIT License © [Estiven Sh](https://github.com/estivensh)
