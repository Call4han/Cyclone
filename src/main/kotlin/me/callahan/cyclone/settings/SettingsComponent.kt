package me.callahan.cyclone.settings

import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import me.callahan.cyclone.icons.Icons
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*

class SettingsComponent : Configurable {

    private lateinit var mainPanel: JPanel
    private var image: Image? = null
    private var imageBack: Image? = null
    private var isModified: Boolean = true
    private var pathImageForward: String = ""
    private var pathImageBackward: String = ""

    // UI Components
    private lateinit var forwardImageLabel: JLabel
    private lateinit var backwardImageLabel: JLabel

    companion object {
        private val MAX_IMAGE_SIZE = Dimension(32, 32)
        private val SUPPORTED_FORMATS = arrayOf("jpg", "jpeg", "png", "gif")
    }

    init {
        createComponents()
        loadExistingImages()
        buildMainPanel()
    }

    private fun createComponents() {
        forwardImageLabel = JLabel()
        backwardImageLabel = JLabel()
    }

    private fun loadExistingImages() {
        loadImageFromConfig(
            ConfigurableState.getInstance().imagePath,
            { Icons.runIcon() },
            forwardImageLabel,
            "Default Forward Icon"
        ) { pathImageForward = it }

        loadImageFromConfig(
            ConfigurableState.getInstance().imagePathBack,
            { Icons.backIcon() },
            backwardImageLabel,
            "Default Backward Icon"
        ) { pathImageBackward = it }
    }

    private fun loadImageFromConfig(
        configPath: String?,
        defaultIconProvider: () -> Icon,
        label: JLabel,
        fallbackText: String,
        pathSetter: (String) -> Unit
    ) {
        if (configPath?.isNotEmpty() == true) {
            val file = File(configPath)
            if (file.exists()) {
                val imageIcon = ImageIcon(file.path)
                image = imageIcon.image
                label.icon = imageIcon
                pathSetter(configPath)
                return
            }
        }

        // Load default icon
        try {
            label.icon = defaultIconProvider()
        } catch (e: Exception) {
            label.text = fallbackText
        }
    }

    private fun buildMainPanel() {
        val forwardPanel = createImagePanel(
            "Forward",
            forwardImageLabel,
            { pathImageForward },
            { path -> pathImageForward = path; image = if (path.isNotEmpty()) ImageIcon(path).image else null },
            { Icons.runIcon() }
        )

        val backwardPanel = createImagePanel(
            "Backward",
            backwardImageLabel,
            { pathImageBackward },
            { path -> pathImageBackward = path; imageBack = if (path.isNotEmpty()) ImageIcon(path).image else null },
            { Icons.backIcon() }
        )

        mainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("→ Forward Image (32x32 max):"), forwardPanel, 1, false)
            .addLabeledComponent(JBLabel("← Backward Image (32x32 max):"), backwardPanel, 1, false)
            .addSeparator(5)
            .addLabeledComponent(JBLabel("Current Forward Image:"), forwardImageLabel, 1, false)
            .addLabeledComponent(JBLabel("Current Backward Image:"), backwardImageLabel, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    private fun createImagePanel(
        direction: String,
        imageLabel: JLabel,
        pathGetter: () -> String,
        pathSetter: (String) -> Unit,
        defaultIconProvider: () -> Icon
    ): JPanel {
        val browseButton = JButton("Browse...")
        val resetButton = JButton("Reset to Default")

        browseButton.addActionListener {
            selectImage(direction, imageLabel, pathSetter)
        }

        resetButton.addActionListener {
            resetToDefault(direction, imageLabel, pathSetter, defaultIconProvider)
        }

        return JPanel(FlowLayout(FlowLayout.LEFT, 5, 0)).apply {
            add(browseButton)
            add(resetButton)
        }
    }

    private fun selectImage(
        direction: String,
        imageLabel: JLabel,
        pathSetter: (String) -> Unit
    ) {
        val descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor().apply {
            title = "Select $direction Image"
            withFileFilter { file -> file.extension in SUPPORTED_FORMATS }
        }

        val selectedFile = FileChooser.chooseFile(descriptor, null, null)?.let {
            VfsUtil.virtualToIoFile(it)
        } ?: return

        try {
            val image = ImageIO.read(selectedFile)
            val imageSize = Dimension(image.width, image.height)
            
            if (imageSize.width > MAX_IMAGE_SIZE.width || imageSize.height > MAX_IMAGE_SIZE.height) {
                Messages.showMessageDialog(
                    "Image is too large (max: ${MAX_IMAGE_SIZE.width}x${MAX_IMAGE_SIZE.height}px)",
                    "Error",
                    Messages.getErrorIcon()
                )
                return
            }

            imageLabel.icon = ImageIcon(image)
            pathSetter(selectedFile.path)
            
        } catch (e: Exception) {
            Messages.showMessageDialog(
                "Failed to load image: ${e.message}",
                "Error",
                Messages.getErrorIcon()
            )
        }
    }

    private fun resetToDefault(
        direction: String,
        imageLabel: JLabel,
        pathSetter: (String) -> Unit,
        defaultIconProvider: () -> Icon
    ) {
        pathSetter("")
        try {
            imageLabel.icon = defaultIconProvider()
            Messages.showInfoMessage(
                "$direction image reset to default cyclone",
                "Reset Successful"
            )
        } catch (e: Exception) {
            imageLabel.text = "Default $direction Icon"
            imageLabel.icon = null
        }
    }

    override fun createComponent(): JComponent? = mainPanel
    override fun isModified(): Boolean = true
    override fun getDisplayName(): String = "Cyclone"

    override fun apply() {
        //save
        ConfigurableState.getInstance().imagePath = 
            if (pathImageForward.isEmpty()) null else pathImageForward
        ConfigurableState.getInstance().imagePathBack = 
            if (pathImageBackward.isEmpty()) null else pathImageBackward
        
        //Icons.resetIcons()
    }

    override fun cancel() {
        isModified = false
    }
}