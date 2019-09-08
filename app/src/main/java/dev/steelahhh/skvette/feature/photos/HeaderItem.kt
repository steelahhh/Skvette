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
import com.google.android.material.button.MaterialButton
import dev.steelahhh.skvette.R
import dev.steelahhh.coreui.epoxy.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_header)
abstract class HeaderItem : EpoxyModelWithHolder<HeaderItem.Holder>() {

    override fun bind(holder: Holder) = with(holder) {
        searchButton.setOnClickListener {  }
        filterButton.setOnClickListener {  }
    }

    override fun unbind(holder: Holder) = with(holder) {
        searchButton.setOnClickListener(null)
        filterButton.setOnClickListener(null)
    }

    class Holder : KotlinEpoxyHolder() {
        val searchButton by bind<MaterialButton>(R.id.searchButton)
        val filterButton by bind<MaterialButton>(R.id.filterButton)
    }
}
