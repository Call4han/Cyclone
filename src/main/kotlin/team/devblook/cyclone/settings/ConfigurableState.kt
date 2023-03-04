package team.devblook.cyclone.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "Settings-Cyclone",
    storages = [Storage("Cyclone.xml")]
)
class ConfigurableState : PersistentStateComponent<ConfigurableState> {
    var user = "Wilder Development"
    var ideaStatus = false
    override fun getState(): ConfigurableState {
        return this
    }

    override fun loadState(state: ConfigurableState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        @JvmStatic
        fun getInstance(): ConfigurableState {
            return ApplicationManager.getApplication().getService(ConfigurableState::class.java)
        }
    }
}