package com.marcospb.peliculasapp.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcospb.data.utils.DispatchersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel (dispatchers: DispatchersProvider):ViewModel(){

    private val io = dispatchers.getIO()
    private val main = dispatchers.getMain()
    private val mainImmediate = dispatchers.getMainImmediate()


    protected fun launchOnIO(block: suspend CoroutineScope.() -> Unit): Job = viewModelScope.launchOnIO(block)
    protected fun launchOnMain(block: suspend CoroutineScope.() -> Unit): Job = viewModelScope.launchOnMain(block)
    protected fun launchOnMainImmediate(block: suspend CoroutineScope.() -> Unit): Job = viewModelScope.launchOnMainImmediate(block)

    protected fun CoroutineScope.launchOnIO(block: suspend CoroutineScope.() -> Unit): Job = launch(io, block = block)
    protected fun CoroutineScope.launchOnMain(block: suspend CoroutineScope.() -> Unit): Job = launch(main, block = block)
    protected fun CoroutineScope.launchOnMainImmediate(block: suspend CoroutineScope.() -> Unit): Job = launch(mainImmediate, block = block)


}