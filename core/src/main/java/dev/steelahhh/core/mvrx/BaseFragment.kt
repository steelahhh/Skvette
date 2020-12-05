/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.core.mvrx

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView

abstract class BaseFragment(@LayoutRes layout: Int = 0) : MavericksView, Fragment(layout) {

  protected val epoxyController by lazy { epoxyController() }

  open fun epoxyController(): MvRxEpoxyController = simpleController { }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    epoxyController.onRestoreInstanceState(savedInstanceState)
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    epoxyController.onSaveInstanceState(outState)
  }

  override fun onDestroyView() {
    epoxyController.cancelPendingModelBuild()
    super.onDestroyView()
  }

  override fun invalidate() {
    epoxyController.requestModelBuild()
  }
}
