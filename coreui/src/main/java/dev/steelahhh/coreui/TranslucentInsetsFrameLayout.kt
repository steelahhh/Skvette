/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.WindowInsets
import android.widget.FrameLayout
import androidx.core.view.updatePadding
import dev.steelahhh.core.statusbar.WindowInsetsHolder
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class TranslucentInsetsFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var statusBarHeight = 0
    private val statusBarPaint = Paint()
    private var drawStatusBar: Boolean = false

    fun updateStatusBar(
        height: Int,
        // colorRes: ColorDesc,
        visible: Boolean
    ) {
        // val color = darkenColor(colorRes.create(context), 0.08f)
        // val shouldInvalidate = statusBarPaint.color != color
        statusBarHeight = height
        // statusBarPaint.color = color
        drawStatusBar = visible
        if (visible) {
            // statusBarPaint.color = Color.TRANSPARENT
        } else {
            statusBarPaint.color = Color.TRANSPARENT
            // statusBarPaint.color = Color.parseColor("#14000000") // 8% of black
        }
        updatePadding(top = if (drawStatusBar) statusBarHeight else 0)
        // if (shouldInvalidate) invalidate()
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        canvas.drawRect(0f, 0f, width.toFloat(), statusBarHeight.toFloat(), statusBarPaint)
    }

    override fun hasOverlappingRendering() = false

    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        WindowInsetsHolder.newInsets(
            Rect(
                insets.systemWindowInsetLeft,
                insets.systemWindowInsetTop,
                insets.systemWindowInsetRight,
                insets.systemWindowInsetBottom
            )
        )
        val bottom = when (insets.systemWindowInsetBottom) {
            0 -> 0
            else -> insets.systemWindowInsetBottom // - ignoredHeight if needed
        }
        updatePadding(bottom = bottom)
        return super.onApplyWindowInsets(insets.consumeSystemWindowInsets())
    }
}
