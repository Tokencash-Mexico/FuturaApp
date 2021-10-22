package com.futura.app.ui.mvi.actions

import com.futura.app.ui.mvi.bases.BaseAction

sealed class HomeAction: BaseAction {
    object NavigateCamera: HomeAction()
}