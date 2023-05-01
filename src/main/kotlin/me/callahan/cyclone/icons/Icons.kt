package me.callahan.cyclone.icons

import com.intellij.util.ui.JBUI
import me.callahan.cyclone.settings.ConfigurableState
import javax.swing.ImageIcon


val CYCLONE_WIDTH by lazy(LazyThreadSafetyMode.NONE) { JBUI.scale(32) }

class Icons {
    companion object {
        val RUN: ImageIcon = ConfigurableState.getInstance().imagePath?.let { ImageIcon(it) }
            ?: ImageIcon(Icons::class.java.getResource("/back-cyclone.gif"))
        val BACK: ImageIcon = ConfigurableState.getInstance().imagePathBack?.let { ImageIcon(it) }
            ?: ImageIcon(Icons::class.java.getResource("/front-cyclone.gif"))
    }
}
