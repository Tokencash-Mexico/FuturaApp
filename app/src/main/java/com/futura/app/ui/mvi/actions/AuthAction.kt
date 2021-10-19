package com.futura.app.ui.mvi.actions

import com.futura.app.ui.mvi.bases.BaseAction

sealed class AuthAction: BaseAction {
    data class Login(val bol: String, val seals: List<String>, val price: String) : AuthAction()
    object NavigateHome: AuthAction()
}