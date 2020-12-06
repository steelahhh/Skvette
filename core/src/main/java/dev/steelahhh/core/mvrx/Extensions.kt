/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.core.mvrx

import androidx.fragment.app.Fragment
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.ViewModelContext
import com.airbnb.mvrx.withState

fun <T : Fragment> ViewModelContext.fragment() = when (this) {
  is FragmentViewModelContext -> fragment<T>()
  else -> error("Cannot get fragment from other ${this.javaClass.simpleName}")
}

open class MavericksEpoxyController(
  val buildModelsCallback: EpoxyController.() -> Unit = {}
) : AsyncEpoxyController() {

  override fun buildModels() {
    buildModelsCallback()
  }
}

/**
 * Create a [MavericksEpoxyController] that builds models with the given callback.
 */
fun BaseFragment.simpleController(
  buildModels: EpoxyController.() -> Unit
) = MavericksEpoxyController {
  // Models are built asynchronously, so it is possible that this is called after the fragment
  // is detached under certain race conditions.
  if (view == null || isRemoving) return@MavericksEpoxyController
  buildModels()
}

/**
 * Create a [MavericksEpoxyController] that builds models with the given callback.
 * When models are built the current state of the viewmodel will be provided.
 */
fun <S : MavericksState, A : MavericksViewModel<S>> BaseFragment.simpleController(
  viewModel: A,
  buildModels: EpoxyController.(state: S) -> Unit
) = MavericksEpoxyController {
  if (view == null || isRemoving) return@MavericksEpoxyController
  withState(viewModel) { state ->
    buildModels(state)
  }
}
