package com.futura.app.ui.mvi.changes

import com.futura.app.ui.mvi.bases.BaseChange
import com.futura.app.ui.mvi.bases.BaseEffect
import com.futura.app.ui.mvi.bases.BaseState
import com.futura.app.ui.mvi.bases.BaseType

data class AuthChange(override val type: BaseType, val errorMessage: String? = null) : BaseChange {

    enum class State: BaseType, BaseState {
        Init, Inactive, Active, Idle, Loading
    }

    enum class Effect: BaseType, BaseEffect {
        Failure, NavigationHome
    }
}