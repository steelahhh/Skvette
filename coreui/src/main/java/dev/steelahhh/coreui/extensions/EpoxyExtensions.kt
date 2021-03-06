/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.extensions

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel

fun EpoxyModel<*>.addIf(
  controller: EpoxyController,
  predicate: () -> Boolean
) = addIf(predicate, controller)
