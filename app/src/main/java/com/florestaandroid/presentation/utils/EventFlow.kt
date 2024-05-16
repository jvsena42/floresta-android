package com.florestaandroid.presentation.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface EventFlow<T> {
    val screenEventFlow: Flow<T>
    fun CoroutineScope.sendEvent(event: T): Job
}

class EventFlowImpl<T> : EventFlow<T> {
    private val eventChannel = Channel<T>(Channel.BUFFERED)
    override val screenEventFlow = eventChannel.receiveAsFlow()

    override fun CoroutineScope.sendEvent(event: T) = launch {
        eventChannel.send(event)
    }
}