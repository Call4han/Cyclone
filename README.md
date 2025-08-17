# 🌪️ Cyclone - IntelliJ Progress Bar Customizer

[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

The **Cyclone** plugin for IntelliJ IDEA allows you to customize the progress bar during project indexing with your own animated images! Transform boring progress bars into dynamic, personalized animations.

## ✨ Features

- 🎨 **Customize progress bar animations** with your own images
- 🔄 **Dual direction support** - different images for forward and backward movement
- 📁 **Multiple format support** - `.gif`, `.png`, `.jpg`, and `.jpeg`
- 🔧 **Easy reset** - quickly return to default cyclone animations
- ⚡ **Optimized performance**
- 🎛️ **Simple configuration** - intuitive settings interface

## 🚀 Installation

### From JetBrains Marketplace (Recommended)
1. Open **IntelliJ IDEA**
2. Go to `File` → `Settings` → `Plugins`
3. Click on `Marketplace` tab
4. Search for "**Cyclone**"
5. Click `Install` and restart IntelliJ IDEA

## 📖 How to Use

1. **Open Settings**
   - Go to `File` → `Settings` → `Editor` → `Cyclone`

2. **Customize Forward Animation**
   - Click `Browse...` next to "Forward Image" 
   - Select your image for rightward progress movement
   - Or click `Reset to Default` to restore original cyclone

3. **Customize Backward Animation**
   - Click `Browse...` next to "Backward Image"
   - Select your image for leftward progress movement
   - Or click `Reset to Default` to restore original cyclone

4. **Apply Changes**
   - Click `Apply` to save your settings
   - Click `OK` to close the dialog
   - Changes apply instantly - restart required!

## 🎨 Image Requirements

- **Maximum size**: 32x32 pixels or smaller
- **Supported formats**: `.gif`, `.png`, `.jpg`, `.jpeg`
- **Recommended**: Use animated GIFs for best visual effect
- **Performance tip**: Keep file sizes small for optimal performance


## ⚠️ Important Notes

- It's recommended to avoid having other plugins that modify the progress bar to prevent conflicts
- Images larger than 32x32 pixels will be rejected during selection
- For best visual results, ensure your images have good contrast with both light and dark themes

## 🐛 Bug Reports & Feature Requests

Found a bug or have a suggestion? We'd love to hear from you!

- **Report bugs**: [Create an issue](https://github.com/Call4han/Cyclone/issues/new?template=bug_report.md)
- **Request features**: [Create an issue](https://github.com/Call4han/Cyclone/issues/new?template=feature_request.md)

## 🛠️ Development

### Prerequisites
- **JDK 21** or higher
- **IntelliJ IDEA** (recommended for development)

### Building from Source
```bash
# Clone the repository
git clone https://github.com/Call4han/Cyclone.git
cd Cyclone

# Build the plugin
./gradlew buildPlugin

# The generated plugin will be in build/libs/
```

### Running in Development
```bash
# Run plugin in a development IntelliJ instance
./gradlew runIde
```

## 🙏 Credits

- **Author**: [Call4han](https://github.com/Call4han)
- **Plugin URL**: [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/21631-cyclone)

## 🌟 Show Your Support

If you find this plugin useful, please:
- ⭐ Star this repository
- 💬 Rate it on the [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/21631-cyclone)

---
