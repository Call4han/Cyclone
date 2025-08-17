package me.callahan.cyclone.ui

import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import javax.swing.UIManager

class ProgressBarStartupActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        updateProgressBarUi()
        val connection = ApplicationManager.getApplication().messageBus.connect()
        connection.subscribe(LafManagerListener.TOPIC, object : LafManagerListener {
            override fun lookAndFeelChanged(p0: LafManager) {
                updateProgressBarUi()
            }
        })

    }

    private fun updateProgressBarUi() {
        UIManager.put("ProgressBarUI", CycloneHandler::class.java.name)
        UIManager.getDefaults()[CycloneHandler::class.java.name] = CycloneHandler::class.java
    }
}
