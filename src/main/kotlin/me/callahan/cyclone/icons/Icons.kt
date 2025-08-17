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
                runIcon = loadForwardIcon()
            }
            return runIcon!!
        }

        fun backIcon(): ImageIcon {
            if (backIcon == null) {
                backIcon = loadBackwardIcon()
            }
            return backIcon!!
        }
    
        private fun loadForwardIcon(): ImageIcon {
            return ConfigurableState.getInstance().imagePath?.let { 
                try {
                    ImageIcon(it)
                } catch (e: Exception) {
                    getDefaultForward()
                }
            } ?: getDefaultForward()
        }

        private fun loadBackwardIcon(): ImageIcon {
            return ConfigurableState.getInstance().imagePathBack?.let { 
                try {
                    ImageIcon(it)
                } catch (e: Exception) {
                    getDefaultBackward()
                }
            } ?: getDefaultBackward()
        }
        //getDefaultForward
        private fun getDefaultForward(): ImageIcon {
            return ImageIcon(Icons::class.java.getResource("/forward-cyclone.gif")!!)
        }
        //getDefaultBackward
        private fun getDefaultBackward(): ImageIcon {
            return ImageIcon(Icons::class.java.getResource("/backward-cyclone.gif")!!)
        }
        
        fun resetIcons() {
            runIcon = null
            backIcon = null
        }
    }
}
