package com.futura.app.ui.mvi.bases

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
@InternalCoroutinesApi
abstract class BaseView<A: BaseAction, C: BaseChange, V: BaseViewModel<A, C>> : Fragment() {

    //BaseViewmodel
    protected abstract val viewModel: V

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        beforeSetup()
        setActors()
        observeChange()
        afterSetup()
    }

    /**
     * Optional funciton to execute code before setActors
     */
    protected open fun beforeSetup() {}

    /**
     * Defines actiors that will be dispatching actions to the [BaseViewModel]
     */
    protected abstract fun setActors()

    /**
     * Starts listening to any change emitted from the [BaseViewModel]
     */
    private fun observeChange() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.changes.collect { change ->
                render(change)
            }
        }
    }

    /**
     * Optional function to execute code after setActors
     */
    protected open fun afterSetup() {}

    /**
     * Renders change received from the [BaseViewModel]
     */
    protected abstract fun render(change: C)

    /**
     * Dispatches an action to the [BaseViewModel]
     */
    protected fun dispatchAction(action: A){
        viewModel.dispatchAction(action)
    }
}