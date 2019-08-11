/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.coreui.epoxy

import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import io.github.coreui.R
import io.github.coreui.R2
import io.github.coreui.getDimen

@EpoxyModelClass(layout = R2.layout.item_loader)
abstract class LoaderItem : EpoxyModelWithHolder<LoaderItem.Holder>() {

    @EpoxyAttribute
    open var isMatchParent: Boolean = false

    override fun bind(holder: Holder) = with(holder) {
        val containerHeight = container.context.getDimen(R.dimen.item_loading_height)
        container.layoutParams = if (isMatchParent)
            LayoutParams(MATCH_PARENT, MATCH_PARENT)
        else
            LayoutParams(MATCH_PARENT, containerHeight)
    }

    class Holder : KotlinEpoxyHolder() {
        val container by bind<LinearLayout>(R.id.progressContainer)
    }
}
