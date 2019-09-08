/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette.feature.photos

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import dev.steelahhh.skvette.R
import io.github.coreui.epoxy.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_header)
abstract class HeaderItem : EpoxyModelWithHolder<HeaderItem.Holder>() {

    override fun bind(holder: Holder) {
    }

    override fun unbind(holder: Holder) {
    }

    class Holder : KotlinEpoxyHolder()
}
