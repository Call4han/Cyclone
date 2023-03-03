package me.callahandev.cyclone.ui

import com.intellij.util.ui.JBUI
import me.callahandev.cyclone.CYCLONE_WIDTH
import me.callahandev.cyclone.DuckIcon
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JComponent
import javax.swing.plaf.basic.BasicProgressBarUI

class DuckProgressBarUi : BasicProgressBarUI() {
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
        val scaledIcon = if (velocity > 0) DuckIcon.RUN else DuckIcon.BACK
        scaledIcon.paintIcon(c, g2d, painter, -JBUI.scale(2))
    }

    private fun drawBackground(g2d: Graphics2D, c: JComponent) {
        //val r = Rectangle(progressBar.size)
        //g2d.color = DUCK_COLOR
        //g2d.fill(RoundRectangle2D.Float(0f, 0f, r.width.toFloat(), r.height.toFloat(), 0f, 0f))
    }

    override fun paint(g: Graphics, c: JComponent) {
        val g2d = g.create() as Graphics2D
        updateX(c.width)
        drawBackground(g2d, c)
        drawIcon(g2d, c)
    }
}