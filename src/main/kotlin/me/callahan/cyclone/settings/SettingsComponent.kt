package me.callahan.cyclone.settings

import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

class SettingsComponent {
    private var mainPanel: JPanel
    val nameText = JBTextField()
    private val box = JBCheckBox("who")

    init {
        mainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Enter user name: "), nameText, 1, false)
            .addComponent(box, 1)
            .addComponentFillVertically(JPanel(), 0)
            .panel;
    }

    fun getPanel(): JPanel {
        return mainPanel
    }

    fun getPreferredFocusedComponent(): JComponent {
        return nameText
    }

    fun getUserNameText(): String = nameText.text

    fun setUserNameText(user: String) {
        nameText.text = user
    }

    fun getIdeaUserStatus(): Boolean {
        return box.isSelected
    }

    fun setIdeaUserStatus(ideaStatus: Boolean) {
        box.isSelected = ideaStatus
    }
}