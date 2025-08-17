package me.callahan.cyclone.ui

import com.intellij.openapi.application.ApplicationManager
import com.intellij.util.ui.JBUI
import me.callahan.cyclone.icons.CYCLONE_WIDTH
import me.callahan.cyclone.icons.Icons
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.swing.Icon
import javax.swing.JComponent
import javax.swing.Timer
import javax.swing.plaf.basic.BasicProgressBarUI

class CycloneProgressBarUI : BasicProgressBarUI() {

    companion object {
        private const val TIMER_DELAY = 20 // 50 FPS
        private const val VELOCITY_STEP = 1
    }

    private var painter = 0
    private var velocity = VELOCITY_STEP
    private var animationTimer: Timer? = null
        
    // Cache
    private var cachedRunIcon: Icon? = null
    private var cachedBackIcon: Icon? = null
    private var iconsLoaded = false

    override fun getPreferredSize(c: JComponent?): Dimension {
        return Dimension(super.getPreferredSize(c).width, JBUI.scale(20))
    }

    override fun installUI(c: JComponent) {
        super.installUI(c)
        loadIcons()
        setupTimer()
    }

    override fun uninstallUI(c: JComponent) {
        cleanup()
        super.uninstallUI(c)
    }

    private fun cleanup() {
        animationTimer?.stop()
        animationTimer = null
        cachedRunIcon = null
        cachedBackIcon = null
    }

    private fun loadIcons() {
        if (!iconsLoaded) {
            // block
            ApplicationManager.getApplication()?.executeOnPooledThread {
                val runIcon = Icons.runIcon()
                val backIcon = Icons.backIcon()

                ApplicationManager.getApplication()?.invokeLater {
                    if (!iconsLoaded) {
                        cachedRunIcon = runIcon
                        cachedBackIcon = backIcon
                        iconsLoaded = true
                    }

                }
            } ?: run {
                cachedRunIcon = Icons.runIcon()
                cachedBackIcon = Icons.backIcon()
                iconsLoaded = true
            }
        }
    }

    private fun setupTimer() {
        if (animationTimer == null) {
            animationTimer = Timer(TIMER_DELAY) {
                if (progressBar?.isDisplayable == true) {
                    updatePosition()
                    progressBar?.repaint()
                } else {
                    stopTimer()
                }

            }
        }
    }

    private fun startTimer() {
        animationTimer?.start()
    }

    private fun stopTimer() {
        animationTimer?.stop()
    }

    private fun updatePosition() {
        val maxX = progressBar?.width ?: return

        painter += velocity

        if (painter <= 0) {
            painter = 0
            velocity = VELOCITY_STEP
        } else if (painter >= maxX - CYCLONE_WIDTH) {
            painter = maxX - CYCLONE_WIDTH
            velocity = -VELOCITY_STEP
        }
    }

    private fun drawIcon(g2d: Graphics2D, c: JComponent) {
        if (!iconsLoaded) return

        val scaledIcon = if (velocity > 0) cachedRunIcon else cachedBackIcon
        scaledIcon?.paintIcon(c, g2d, painter, -JBUI.scale(2))
    }

    override fun paint(g: Graphics, c: JComponent) {
        if (!c.isDisplayable) return

        val g2d = g.create() as Graphics2D
        try {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

            
            drawIcon(g2d, c)

            
            if (animationTimer?.isRunning != true) {
                startTimer()
            }

        } finally {
            g2d.dispose()
        }
    }
    
    override fun setAnimationIndex(newValue: Int) {
        super.setAnimationIndex(newValue)
        if (progressBar?.isShowing == true) {
            if (animationTimer?.isRunning != true) {
                startTimer()
            }
        } else {
            stopTimer()
        }
    }
}