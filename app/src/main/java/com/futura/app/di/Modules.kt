package com.futura.app.di

import androidx.lifecycle.SavedStateHandle
import com.futura.app.ui.mvi.changes.AuthChange
import com.futura.app.ui.mvi.changes.HomeChange
import com.futura.app.ui.mvi.changes.SplashChange
import com.futura.app.ui.viewmodel.AuthViewModel
import com.futura.app.ui.viewmodel.HomeViewModel
import com.futura.app.ui.viewmodel.SplashViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
object Modules {
    val applicationModule = module {}

    val uiModule = module {}

    val domainModule = module {}

    val dataModule = module {}

    val commonModule = module {}

    val viewModelModule = module {
        viewModel { (handle: SavedStateHandle) ->
            SplashViewModel(
                androidContext(),
                SplashChange(SplashChange.State.Init)
            )
        }

        viewModel { (handle: SavedStateHandle) ->
            AuthViewModel(
                androidContext(),
                AuthChange(AuthChange.State.Init)
            )
        }

        viewModel { (handle: SavedStateHandle) ->
            HomeViewModel(
                androidContext(),
                HomeChange(HomeChange.State.Init)
            )
        }
    }
}