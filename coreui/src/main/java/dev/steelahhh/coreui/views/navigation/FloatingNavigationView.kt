/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.views.navigation

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.Px
import androidx.constraintlayout.widget.ConstraintLayout
import dev.steelahhh.core.DrawableRef
import dev.steelahhh.coreui.R
import dev.steelahhh.coreui.extensions.dp

// TODO: consider introducing compose, and making this a @Composable
class FloatingNavigationView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
  defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
  @Px
  var spacing: Int = 16.dp

  @Volatile
  var isShowing: Boolean = true
    private set

  var onSelectItem: (item: FloatingNavigationItem) -> Unit = {
    selectedItem = it
  }

  var items: List<FloatingNavigationItem> = emptyList()
    set(value) {
      createItems(value)
      field = value
    }

  var selectedItem: FloatingNavigationItem? = null
    set(value) {
      field = value
      items = items.map {
        when (it) {
          is FloatingNavigationItem.Icon -> it.copy(_isActive = value == it)
          is FloatingNavigationItem.Custom -> it.copy(_isActive = value == it)
        }
      }
    }

  init {
    setBackgroundResource(R.drawable.rounded_rect_surface)
  }

  private fun createItems(items: List<FloatingNavigationItem>) {
    if (selectedItem == null) selectedItem = items.find { it.isActive }
    removeAllViews()
    items.forEachIndexed { idx, item ->
      when (item) {
        is FloatingNavigationItem.Icon -> createIconView(idx, items, item)
        is FloatingNavigationItem.Custom -> addView(item.view)
      }
    }
  }

  private fun createIconView(
    idx: Int,
    items: List<FloatingNavigationItem>,
    item: FloatingNavigationItem.Icon
  ) {
    val containerView = FrameLayout(context).apply {
      layoutParams = ConstraintLayout.LayoutParams(CONTAINER_SIZE, CONTAINER_SIZE).apply {
        marginEnd = if (idx != items.lastIndex) spacing else 0
      }
    }

    val imageView = ImageView(context)

    val icon = item.icon.create(context)?.apply {
      val tint = if (item.isActive) {
        item.selectionColor.create(context)
      } else {
        item.iconTint.create(context)
      }
      mutate().setColorFilter(tint, PorterDuff.Mode.SRC_IN)
    }

    imageView.setImageDrawable(icon)

    containerView.run {
      setOnClickListener { onSelectItem(item) }
      background = DrawableRef.Attribute(R.attr.selectableItemBackgroundBorderless).create(context)
      addView(
        imageView,
        FrameLayout.LayoutParams(ICON_SIZE, ICON_SIZE).apply {
          gravity = Gravity.CENTER
        }
      )
    }

    addView(containerView)
  }

  fun hide() {
    if (isShowing) {
      animate()
        .y((parent as ViewGroup).height.toFloat() + height + 32.dp)
        .scaleX(FINAL_SCALE)
        .scaleY(FINAL_SCALE)
        .alpha(FINAL_SCALE)
        .setDuration(ANIMATION_DURATION)
        .start()
      isShowing = false
    }
  }

  fun show() {
    if (!isShowing) {
      animate()
        .y((parent as ViewGroup).height.toFloat() - height - 16.dp)
        .scaleX(INITIAL_SCALE)
        .scaleY(INITIAL_SCALE)
        .alpha(INITIAL_SCALE)
        .setDuration(ANIMATION_DURATION)
        .start()
      isShowing = true
    }
  }

  companion object {
    const val ANIMATION_DURATION = 200L

    private const val INITIAL_SCALE = 1f
    private const val FINAL_SCALE = 0.5f

    private val ICON_SIZE = 24.dp
    private val CONTAINER_SIZE = 42.dp
  }
}
