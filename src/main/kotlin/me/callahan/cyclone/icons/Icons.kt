package me.callahan.cyclone.icons

import com.intellij.util.ui.JBUI
import me.callahan.cyclone.settings.ConfigurableState
import javax.swing.ImageIcon


val CYCLONE_WIDTH by lazy(LazyThreadSafetyMode.NONE) { JBUI.scale(32) }

class Icons {
    companion object {
        private var runIcon: ImageIcon? = null
        private var backIcon: ImageIcon? = null

        fun runIcon(): ImageIcon {
            if (runIcon == null) {
                runIcon = ConfigurableState.getInstance().imagePath?.let { ImageIcon(it) }
                    ?: ImageIcon(Icons::class.java.getResource("/front-cyclone.gif"))
            }
            return runIcon!!
        }

        fun backIcon(): ImageIcon {
            if (backIcon == null) {
                backIcon = ConfigurableState.getInstance().imagePathBack?.let { ImageIcon(it) }
                    ?: ImageIcon(Icons::class.java.getResource("/back-cyclone.gif"))
            }
            return backIcon!!
        }
    }
}
