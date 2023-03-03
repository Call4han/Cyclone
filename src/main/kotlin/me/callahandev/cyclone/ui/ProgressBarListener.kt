package me.callahandev.cyclone.ui

import com.intellij.ide.ui.LafManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.BaseComponent
import javax.swing.UIManager

class ProgressBarListener : BaseComponent {
    init {
        LafManager.getInstance().addLafManagerListener {
            updateProgressBarUi()
        }
    }

    companion object {
        fun getInstance(): ProgressBarListener {
            return ApplicationManager.getApplication().getComponent(ProgressBarListener::class.java)
        }
    }

    override fun initComponent() {
        updateProgressBarUi()
    }

    private fun updateProgressBarUi() {
        UIManager.put("ProgressBarUI", CycloneHandler::class.java.name)
        UIManager.getDefaults()[CycloneHandler::class.java.name] = CycloneHandler::class.java
    }


    override fun getComponentName(): String = "DuckLafUpdater"
}
