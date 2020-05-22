/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.core.mvrx

import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Success
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class MvRxViewModel<S : MvRxState>(
    initialState: S
) : BaseMvRxViewModel<S>(initialState) {
    @Deprecated("This will be introduced in MvRx v2.0. Use with caution")
    fun <T : Any?> (suspend () -> T).execute(
        dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
        reducer: S.(Async<T>) -> S
    ): Job {
        setState { reducer(Loading()) }
        return viewModelScope.launch(dispatcher) {
            try {
                val result = invoke()
                setState { reducer(Success(result)) }
            } catch (e: CancellationException) {
                @Suppress("RethrowCaughtException")
                throw e
            } catch (@Suppress("TooGenericExceptionCaught") e: Exception) {
                setState { reducer(Fail(e)) }
            }
        }
    }
}
