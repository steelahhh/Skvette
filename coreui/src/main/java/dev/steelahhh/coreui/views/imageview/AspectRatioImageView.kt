/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.views.imageview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import dev.steelahhh.coreui.views.imageview.AspectRatioDimensionsCalculator.Companion.INVALID_RATIO

class AspectRatioImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val dimensionsCalculator = AspectRatioDimensionsCalculator()

    var ratio: Float = INVALID_RATIO
        set(value) {
            dimensionsCalculator.checkAspectRatio(value)
            field = value
            requestLayout()
        }

    fun setAspectRatio(width: Int, height: Int) {
        dimensionsCalculator.checkAspectRatio(width, height)
        ratio = dimensionsCalculator.getAspectRatio(width, height)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (ratio != INVALID_RATIO) {
            val (width, height) = dimensionsCalculator.measureDimensions(
                widthMeasureSpec,
                heightMeasureSpec,
                ratio
            )
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}
