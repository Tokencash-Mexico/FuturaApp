package com.futura.app.ui.view.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.futura.app.R
import com.futura.app.ui.mvi.actions.SplashAction
import com.futura.app.ui.mvi.bases.BaseView
import com.futura.app.ui.mvi.changes.SplashChange
import com.futura.app.ui.viewmodel.SplashViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import org.koin.androidx.viewmodel.ext.android.stateViewModel

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@FlowPreview
class SplashFragment : BaseView<SplashAction, SplashChange, SplashViewModel>() {

    override val viewModel: SplashViewModel by stateViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun setActors() {

    }

    override fun render(change: SplashChange) {
        when(change.type){
            SplashChange.State.Init -> {
                renderInit()
            }
            SplashChange.Effect.NavigateSettings -> {
                renderNavigateHome()
            }
        }
    }

    private fun renderInit() {
        // add timer splash and dispatch navigate
        Handler(Looper.getMainLooper()).postDelayed({
            dispatchAction(SplashAction.NavigateAuth)
        }, 1000)
    }

    private fun renderNavigateHome() {
        findNavController().navigate(R.id.action_splashFragment_to_authFramgent)
    }
}