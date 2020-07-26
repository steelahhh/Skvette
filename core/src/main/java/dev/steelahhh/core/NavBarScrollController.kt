/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.core

import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object NavBarScrollController {
    private val stateFlow = MutableStateFlow(ScrollDirection.IDLE)

    fun flow(): Flow<ScrollDirection> = stateFlow

    fun accept(direction: ScrollDirection) {
        stateFlow.value = direction
    }

    enum class ScrollDirection {
        UP, DOWN, IDLE
    }
}

object RecyclerScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        NavBarScrollController.accept(
            when {
                dy < 0 -> NavBarScrollController.ScrollDirection.UP
                dy > 0 -> NavBarScrollController.ScrollDirection.DOWN
                else -> NavBarScrollController.ScrollDirection.IDLE
            }
        )
    }
}

fun RecyclerView.attachNavBarController() {
    removeOnScrollListener(RecyclerScrollListener)
    addOnScrollListener(RecyclerScrollListener)
}
