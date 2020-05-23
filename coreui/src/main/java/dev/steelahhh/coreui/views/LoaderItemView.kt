/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import dev.steelahhh.coreui.R
import dev.steelahhh.coreui.extensions.getDimen

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class LoaderItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    var isMatchParent: Boolean = false
        @ModelProp set

    init {
        View.inflate(context, R.layout.item_loader, this)
        orientation = VERTICAL
        setPadding(context.getDimen(R.dimen.spacing_xm))
    }

    @ModelProp
    fun setIsMatchParent(isMatchParent: Boolean) {
        val containerHeight = context.getDimen(R.dimen.item_loading_height)
        layoutParams = when {
            isMatchParent -> ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            else -> ViewGroup.LayoutParams(MATCH_PARENT, containerHeight)
        }
    }
}
