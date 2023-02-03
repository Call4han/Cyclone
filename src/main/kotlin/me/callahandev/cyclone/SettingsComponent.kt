package me.callahandev.cyclone

import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel

class SettingsComponent {
    private var mePanel: JPanel
    private var projectFormatText = JBTextField()
    private var fileFormatText = JBTextField()

    init {
        mePanel = FormBuilder.createFormBuilder()
            .addComponent(JBLabel("Customize how the title of your IDE is formatted."))
            .addComponent(JBLabel("You can use arbitrary characters and the following variables:"))
            .addComponent(JBLabel("{DEFAULT} - Use the IDE default"))
            .addComponent(JBLabel("{SIMPLE} - Just the name (not the path of the project or file)"))
            .addComponent(JBLabel(" "))
            .addComponent(JBLabel(" "))
            .addLabeledComponent(JBLabel("Project Format"), projectFormatText, 1, false)
            .addLabeledComponent(JBLabel("File Format"), fileFormatText, 1, false)
            .addComponent(JBLabel(" "))
            .addComponent(JBLabel("Leave format blank to omit that data from the title"))
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    fun getPanel(): JPanel {
        return mePanel
    }

    fun getPreferredFocusedComponent(): JBTextField {
        return projectFormatText
    }

    fun getProjectFormat(): String {
        return projectFormatText.text
    }

    fun setProjectFormat(format: String) {
        projectFormatText.text = format
    }

    fun getFileFormat(): String {
        return fileFormatText.text
    }

    fun setFileFormat(format: String) {
        fileFormatText.text = format
    }

}