package com.futura.app.ui.viewmodel

import android.content.Context
import com.futura.app.ui.mvi.actions.AuthAction
import com.futura.app.ui.mvi.bases.BaseViewModel
import com.futura.app.ui.mvi.changes.AuthChange
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.coroutines.EmptyCoroutineContext

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class AuthViewModel(
    private val context: Context,
    initialState: AuthChange
) : BaseViewModel<AuthAction, AuthChange>(initialState) {

    override suspend fun handleActions(action: AuthAction): Flow<AuthChange> {
        return when (action){
            is AuthAction.NavigateHome -> handleNavigateHome()
            is AuthAction.Login -> handleLogin(action)
        }
    }

    override fun reduce(previousChange: AuthChange, newChange: AuthChange): AuthChange {
        return newChange
    }

    private fun handleNavigateHome(): Flow<AuthChange> {
        return flowOf(AuthChange(AuthChange.Effect.NavigationHome))
    }

    private fun handleLogin(action: AuthAction) : Flow<AuthChange>{
        return flowOf()
    }
}