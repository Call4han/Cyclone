package me.callahan.cyclone.ui

import javax.swing.JComponent
import javax.swing.plaf.basic.BasicProgressBarUI

class CycloneHandler : BasicProgressBarUI() {
    companion object {
        @Suppress("UNUSED_PARAMETER")
        @JvmStatic
        fun createUI(c: JComponent): BasicProgressBarUI {
            return DuckProgressBarUi()
        }
    }
}