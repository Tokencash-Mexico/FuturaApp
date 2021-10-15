package com.futura.app.ui.mvi.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
abstract class BaseViewModel<A: BaseAction, C: BaseChange>(initialState: C) : ViewModel() {

    /**
     * Stream of all the actions dispatched from the [BaseView]
     */
    protected val actions = BroadcastChannel<A>(1)

    /**
     * Stream of all the effects
     */
    protected val effects = BroadcastChannel<C>(1)

    /**
     * Stream of all the state
     */
    protected val states = ConflatedBroadcastChannel(initialState)

    /**
     * Stream of the conjunction if [effects] and [states]
     */
    val changes = merge(effects.asFlow(), states.asFlow())

    init {
        setup()
    }

    /**
     * When [dispatchAction] is called, converts tje action into a flow that can be emitted to the
     * [actions] stream
     *
     * After the action is emitted, the [handleActions] function is called to handle the action
     *
     * Each time a change is collected, is dispatched accordingly to it's
     * type [BaseState] or [BaseEffect]
     */
    private fun setup(){
        viewModelScope.launch {
            actions.asFlow().flatMapMerge { action ->
                handleActions(action)
            }.collect {
                if (it.type is BaseState){
                    dispatchChange(reduce(states.value, it))
                }else{
                    dispatchChange(it)
                }
            }
        }
    }

    /**
     * Creates new change if needed using the previous change and the current change
     */
    protected abstract fun reduce(previousChange: C, newChange: C):C

    /**
     * Receives all actions from the [BaseView] to be handle
     */
    protected abstract suspend fun handleActions(action:A):Flow<C>

    /**
     * Sends the actions from the [BaseView] into the [actions] stream
     */
    fun dispatchAction(action: A){
        viewModelScope.launch {
            actions.send(action)
        }
    }

    /**
     * Dispatches a change to the corresponding stream
     */
    protected suspend fun dispatchChange(change:C){
        when(change.type){
            is BaseState -> dispatchState(change)
            is BaseEffect -> dispatchEffect(change)
        }
    }

    private suspend fun dispatchEffect(effect: C){
        effects.send(effect)
    }

    private suspend fun dispatchState(state: C){
        states.send(state)
    }
}