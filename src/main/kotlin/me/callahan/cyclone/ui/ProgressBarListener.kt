package me.callahan.cyclone.ui

import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.openapi.application.ApplicationManager
import javax.swing.UIManager

class ProgressBarListener : LafManagerListener {
    init {
        updateProgressBarUi()
    }

    private fun updateProgressBarUi() {
        UIManager.put("ProgressBarUI", CycloneHandler::class.java.name)
        UIManager.getDefaults()[CycloneHandler::class.java.name] = CycloneHandler::class.java
    }

    override fun lookAndFeelChanged(source: LafManager) {
        updateProgressBarUi()
    }


}
