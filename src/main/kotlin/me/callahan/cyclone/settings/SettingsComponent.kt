package me.callahan.cyclone.settings

import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import java.awt.Dimension
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*

class SettingsComponent : Configurable {

    private var mainPanel: JPanel
    private var image: Image? = null
    private var imageBack: Image? = null
    private var isModified: Boolean = true
    private lateinit var pathImageFront: String
    private lateinit var pathImageBack: String

    init {
        val maxSize = Dimension(32, 32)
        val imageLabel = JLabel()
        val imageButton = JButton("Click Here to Select")
        val descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
        descriptor.title = "Select Image"
        descriptor.withFileFilter { file -> file.extension in arrayOf("jpg", "jpeg", "png", "gif") }

        imageButton.addActionListener {
            val selectedFileVirtual = FileChooser.chooseFile(descriptor, null, null)
            if (selectedFileVirtual != null) {
                val fileSelected = VfsUtil.virtualToIoFile(selectedFileVirtual)
                val image = ImageIO.read(fileSelected)
                val imageSize = Dimension(image.width, image.height)
                if (imageSize.width > maxSize.width || imageSize.height > maxSize.height) {
                    Messages.showMessageDialog("Image is too large", "Error", Messages.getErrorIcon())
                } else {
                    this.image = image
                    imageLabel.icon = ImageIcon(image)
                    this.pathImageFront = fileSelected.path
                }
            }
        }

        val imageBackLabel = JLabel()
        val imageBackButton = JButton("Click Here to Select")

        imageBackButton.addActionListener {
            val selectedFileVirtual = FileChooser.chooseFile(descriptor, null, null)
            if (selectedFileVirtual != null) {
                val selectedFile = VfsUtil.virtualToIoFile(selectedFileVirtual)
                val image = ImageIO.read(selectedFile)
                val imageSize = Dimension(image.width, image.height)
                if (imageSize.width > maxSize.width || imageSize.height > maxSize.height) {
                    Messages.showMessageDialog("Image is too large", "Error", Messages.getErrorIcon())
                } else {
                    this.imageBack = image
                    imageBackLabel.icon = ImageIcon(image)
                    this.pathImageBack = selectedFile.path
                }
            }
        }
        val imagePath = ConfigurableState.getInstance().imagePath
        if (imagePath != null) {
            val file = File(imagePath)
            if (file.exists()) {
                image = ImageIcon(file.path).image
                imageLabel.icon = ImageIcon(image)
            }
        }

        val imagePathBack = ConfigurableState.getInstance().imagePathBack
        if (imagePathBack != null) {
            val file = File(imagePathBack)
            if (file.exists()) {
                imageBack = ImageIcon(file.path).image
                imageBackLabel.icon = ImageIcon(imageBack)
            }
        }
        this.mainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Select Front Image: "), imageButton, 1, false)
            .addLabeledComponent(JBLabel("Select Back Image: "), imageBackButton, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .addLabeledComponent(JBLabel("Current Front Image: "), imageLabel, 1, false)
            .addLabeledComponent(JBLabel("Current Back Image: "), imageBackLabel, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }


    override fun createComponent(): JComponent? {
        return mainPanel
    }

    override fun isModified(): Boolean {
        return true
    }

    override fun apply() {
        if (this::pathImageFront.isInitialized) {
            ConfigurableState.getInstance().imagePath = this.pathImageFront
        }
        if (this::pathImageBack.isInitialized) {
            ConfigurableState.getInstance().imagePathBack = this.pathImageBack
        }

    }

    override fun cancel() {
        this.isModified = false
    }

    override fun getDisplayName(): String {
        return "Cyclone"
    }


}
