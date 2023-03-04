package team.devblook.cyclone.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

class SettingsConfigurableExample : Configurable {
    private lateinit var settingsComponent: SettingsComponent
    override fun createComponent(): JComponent {
        settingsComponent = SettingsComponent()
        return settingsComponent.getPanel()

    }

    override fun isModified(): Boolean {
        val settings = ConfigurableState.getInstance()
        return !settingsComponent.nameText.equals(settings.user) ||
                settingsComponent.getIdeaUserStatus() != settings.ideaStatus

    }

    override fun apply() {
        val settings = ConfigurableState.getInstance()
        settings.user = settingsComponent.getUserNameText()
        settings.ideaStatus = settingsComponent.getIdeaUserStatus()
        println(settings.user + "apply")

    }


    override fun reset() {
        val settings = ConfigurableState.getInstance()
        settingsComponent.setUserNameText(settings.user)
        settingsComponent.setIdeaUserStatus(settings.ideaStatus)

    }


    @Nls(capitalization = Nls.Capitalization.Title)
    override fun getDisplayName(): String {
        return "Example Settings"
    }

    override fun getPreferredFocusedComponent(): JComponent? {
        return settingsComponent.getPreferredFocusedComponent()
    }
}