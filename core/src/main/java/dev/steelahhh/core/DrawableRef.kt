/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.core

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

sealed class DrawableRef {
  data class Attribute(@AttrRes val attr: Int) : DrawableRef() {
    override fun create(context: Context): Drawable? {
      val typedValue = TypedValue()
      val theme = context.theme
      theme.resolveAttribute(attr, typedValue, true)
      return ContextCompat.getDrawable(context, typedValue.resourceId)
    }
  }

  data class ColorResource(val color: ColorRef) : DrawableRef() {
    override fun create(context: Context): Drawable? = ColorDrawable(color.create(context))
  }

  data class Resource(@DrawableRes val drawableRes: Int) : DrawableRef() {
    override fun create(context: Context): Drawable? = ContextCompat.getDrawable(context, drawableRes)
  }

  data class Bitmap(val bitmap: android.graphics.Bitmap) : DrawableRef() {
    override fun create(context: Context): Drawable? = BitmapDrawable(context.resources, bitmap)
  }

  abstract fun create(context: Context): Drawable?
}

fun Int.toResourceDrawableDesc() = DrawableRef.Resource(this)
fun Int.toResourceColorDrawableDesc() = DrawableRef.ColorResource(this.toResourceColorRef())
