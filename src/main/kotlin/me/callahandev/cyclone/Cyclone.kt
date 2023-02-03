package me.callahandev.cyclone

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.impl.PlatformFrameTitleBuilder


class Cyclone : PlatformFrameTitleBuilder() {


    private val state = TitleSettingsState.getInstance()
    override fun getProjectTitle(project: Project): String {
        var title = state.projectFormat
        if (title.contains("{DEFAULT}")) {
            title = title.replace("{DEFAULT}", super.getProjectTitle(project)!!)
        }
        if (title.contains("{SIMPLE}")) {
            title = title.replace("{SIMPLE}", project.name)
        }
        return title
    }
    override fun getFileTitle(project: Project, file: VirtualFile): String {
        var title = state.fileFormat
        if (title.contains("{DEFAULT}")) {
            title = title.replace("{DEFAULT}", super.getFileTitle(project, file)!!)
        }
        if (title.contains("{SIMPLE}")) {
            title = title.replace("{SIMPLE}", file.name)
        }
        return title
    }


}