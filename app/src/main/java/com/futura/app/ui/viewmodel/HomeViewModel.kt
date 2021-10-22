package com.futura.app.ui.viewmodel

import android.content.Context
import com.futura.app.ui.mvi.actions.HomeAction
import com.futura.app.ui.mvi.bases.BaseView
import com.futura.app.ui.mvi.bases.BaseViewModel
import com.futura.app.ui.mvi.changes.HomeChange
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class HomeViewModel(
    private val context: Context,
    initialState: HomeChange
) : BaseViewModel<HomeAction, HomeChange>(initialState) {

    override suspend fun handleActions(action: HomeAction): Flow<HomeChange> {
        return when(action){
            is HomeAction.NavigateCamera -> handleNavigateCamera()
        }
    }

    override fun reduce(previousChange: HomeChange, newChange: HomeChange): HomeChange {
        return newChange
    }

    private fun handleNavigateCamera(): Flow<HomeChange> {
        return flowOf()
    }
}