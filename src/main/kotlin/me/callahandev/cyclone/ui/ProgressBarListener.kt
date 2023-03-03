package me.callahandev.cyclone.ui

import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.openapi.application.ApplicationManager
import javax.swing.UIManager

class ProgressBarListener : LafManagerListener {
    init {
        updateProgressBarUi()
    }

    companion object {
        fun getInstance(): ProgressBarListener {
            return ApplicationManager.getApplication().getComponent(ProgressBarListener::class.java)
        }
    }


    private fun updateProgressBarUi() {
        UIManager.put("ProgressBarUI", CycloneHandler::class.java.name)
        UIManager.getDefaults()[CycloneHandler::class.java.name] = CycloneHandler::class.java
    }
    override fun lookAndFeelChanged(source: LafManager) {
        updateProgressBarUi()
    }


}
