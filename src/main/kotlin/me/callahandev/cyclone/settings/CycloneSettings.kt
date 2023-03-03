package me.callahandev.cyclone.settings

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

class CycloneSettings : Configurable {
    private val textField = JTextField()
    private lateinit var settings: SettingsComponent
    override fun createComponent(): JComponent {
        textField.preferredSize = Dimension(100, 30)
        val panel = JPanel()
        panel.add(textField)
        return panel
    }

    override fun isModified(): Boolean {
        return true
    }

    override fun apply() {
        val message = textField.text
        val value = PropertiesComponent.getInstance().setValue("cyclone.message", message, "Starting")
        PropertiesComponent.getInstance().saveFields(value)
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    override fun getDisplayName(): String {
        return "Cyclone"
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return settings.getPreferredFocusedComponent()
    }

    override fun reset() {
        val message = PropertiesComponent.getInstance().getValue("cyclone.message", "Default")
        textField.text = message
    }


}
