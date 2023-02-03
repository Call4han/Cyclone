package me.callahandev.cyclone

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.ex.ProjectEx
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

class TitleConfiguration : Configurable {
    private lateinit var settingsComponent: SettingsComponent
    @Nls(capitalization = Nls.Capitalization.Title)
    override fun getDisplayName(): String {
        return "Simple Title Format"
    }
    override fun getPreferredFocusedComponent(): JComponent {
        return settingsComponent.getPreferredFocusedComponent()
    }
    override fun createComponent(): JComponent {
        settingsComponent = SettingsComponent()
        return settingsComponent.getPanel()
    }
    override fun isModified(): Boolean {
        val state = TitleSettingsState.getInstance()
        return !settingsComponent.getProjectFormat().equals(state.projectFormat) || !settingsComponent.getFileFormat().equals(state.fileFormat)
    }

    override fun apply() {
        TitleSettingsState.getInstance().fileFormat = settingsComponent.getFileFormat()
        TitleSettingsState.getInstance().projectFormat = settingsComponent.getProjectFormat()
        triggerTitleRefresh()
    }


    private fun triggerTitleRefresh() {
        val firstProject = ProjectManager.getInstance().openProjects[0]
        if (firstProject is ProjectEx){
              val oldName = firstProject.name
            firstProject.setProjectName("")
            firstProject.setProjectName(oldName)
        }
    }
    override fun reset() {
        val state = TitleSettingsState.getInstance()
        settingsComponent.setProjectFormat(state.projectFormat)
        settingsComponent.setFileFormat(state.fileFormat)
    }
    override fun disposeUIResources() {
        settingsComponent = null!!
    }
}