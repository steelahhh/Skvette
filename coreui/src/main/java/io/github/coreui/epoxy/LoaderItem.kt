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
import io.github.coreui.R
import io.github.coreui.getDimen

data class LoaderItem(
    var isMatchParent: Boolean = false
) : KotlinModel(R.layout.item_loader) {

    private val container by bind<LinearLayout>(R.id.progressContainer)

    override fun bind() {
        val containerHeight = container.context.getDimen(R.dimen.item_loading_height)
        container.layoutParams = if (isMatchParent)
            LayoutParams(MATCH_PARENT, MATCH_PARENT)
        else
            LayoutParams(MATCH_PARENT, containerHeight)
    }
}
