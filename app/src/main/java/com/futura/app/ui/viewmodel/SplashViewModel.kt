package com.futura.app.ui.viewmodel

import android.content.Context
import com.futura.app.ui.mvi.actions.SplashAction
import com.futura.app.ui.mvi.bases.BaseViewModel
import com.futura.app.ui.mvi.changes.SplashChange
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class SplashViewModel(
    private val context: Context,
    initialState: SplashChange
) : BaseViewModel<SplashAction, SplashChange>(initialState) {

    override suspend fun handleActions(action: SplashAction): Flow<SplashChange> {
        return when (action) {
            is SplashAction.NavigateAuth -> handleNavigateSettings()
        }
    }

    override fun reduce(previousChange: SplashChange, newChange: SplashChange): SplashChange {
        return newChange
    }

    private fun handleNavigateSettings(): Flow<SplashChange> {
        return flowOf(SplashChange(SplashChange.Effect.NavigateSettings))
    }
}