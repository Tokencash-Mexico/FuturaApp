package com.futura.app.ui.mvi.actions

import com.futura.app.ui.mvi.bases.BaseAction

sealed class SplashAction : BaseAction {
    object NavigateAuth : SplashAction()
}