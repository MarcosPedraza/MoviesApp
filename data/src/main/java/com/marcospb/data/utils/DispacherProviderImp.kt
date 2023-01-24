package com.marcospb.data.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispacherProviderImp : DispatchersProvider {
    override fun getIO(): CoroutineDispatcher = Dispatchers.IO

    override fun getMain(): CoroutineDispatcher = Dispatchers.Main

    override fun getMainImmediate(): CoroutineDispatcher = Dispatchers.Main.immediate

    override fun getDefault(): CoroutineDispatcher = Dispatchers.Default
}