package team.devblook.cyclone

import com.intellij.util.ui.JBUI
import javax.swing.ImageIcon


val CYCLONE_WIDTH by lazy(LazyThreadSafetyMode.NONE) { JBUI.scale(32) }

class DuckIcon {
    companion object {
        val RUN: ImageIcon = ImageIcon(DuckIcon::class.java.getResource("/mario.gif"))
        val BACK: ImageIcon = ImageIcon(DuckIcon::class.java.getResource("/shell.gif"))
    }
}
