package me.callahandev.cyclone

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "me.callahandev.TitleSettingsState", storages = [Storage("cyclone.xml")])
class TitleSettingsState : PersistentStateComponent<TitleSettingsState>{
    var projectFormat = "{DEFAULT}"
    var fileFormat = "{SIMPLE}"
    override fun getState(): TitleSettingsState? {
        return this
    }

    override fun loadState(state: TitleSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }
    companion object{
        fun getInstance(): TitleSettingsState {
            return ApplicationManager.getApplication().getService(TitleSettingsState::class.java)
        }
    }

}