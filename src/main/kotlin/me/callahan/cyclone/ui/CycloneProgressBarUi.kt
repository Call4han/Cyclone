package me.callahan.cyclone.ui

import com.intellij.util.ui.JBUI
import me.callahan.cyclone.icons.CYCLONE_WIDTH
import me.callahan.cyclone.icons.Icons
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JComponent
import javax.swing.plaf.basic.BasicProgressBarUI

class CycloneProgressBarUi : BasicProgressBarUI() {
    override fun getPreferredSize(c: JComponent?): Dimension {
        return Dimension(super.getPreferredSize(c).width, JBUI.scale(20))
    }

    private var painter = 0
    private var velocity = 1

    private fun updateX(maxX: Int) {
        painter += velocity
        if (painter <= 0) {
            painter = 0
            velocity = 1
        } else if (painter >= maxX - CYCLONE_WIDTH) {
            painter = maxX - CYCLONE_WIDTH
            velocity = -1
        }
    }

    private fun drawIcon(g2d: Graphics2D, c: JComponent) {
        val scaledIcon = if (velocity > 0) Icons.runIcon() else Icons.backIcon()
        scaledIcon.paintIcon(c, g2d, painter, -JBUI.scale(2))
    }


    override fun paint(g: Graphics, c: JComponent) {
        val g2d = g.create() as Graphics2D
        updateX(c.width)
        drawIcon(g2d, c)
    }
}