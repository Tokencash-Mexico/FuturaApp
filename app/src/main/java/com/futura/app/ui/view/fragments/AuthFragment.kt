package com.futura.app.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.futura.app.R
import com.futura.app.ui.mvi.actions.AuthAction
import com.futura.app.ui.mvi.bases.BaseView
import com.futura.app.ui.mvi.changes.AuthChange
import com.futura.app.ui.viewmodel.AuthViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.stateViewModel

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@FlowPreview
class AuthFragment: BaseView<AuthAction, AuthChange, AuthViewModel>() {

    override val viewModel: AuthViewModel by stateViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun setActors() {

    }

    override fun render(change: AuthChange) {
        when(change.type){
            AuthChange.State.Init -> {

            }
            AuthChange.Effect.NavigationHome -> {

            }
        }
    }
}