/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.core

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Parcelable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import kotlinx.android.parcel.Parcelize

sealed class ColorRef : Parcelable {

  @Parcelize
  data class Resource(@ColorRes val resource: Int, val isColorStateList: Boolean = false) : ColorRef() {
    override fun create(context: Context): Int = ContextCompat.getColor(context, resource)

    override fun asColorStateList(
      context: Context
    ): ColorStateList? = ContextCompat.getColorStateList(context, resource)
  }

  @Parcelize
  data class Raw(@ColorInt val color: Int) : ColorRef() {
    override fun create(context: Context): Int = color
  }

  @Parcelize
  data class Attribute(@AttrRes val attr: Int) : ColorRef() {
    override fun create(context: Context): Int {
      val typedValue = TypedValue()
      val theme = context.theme
      theme.resolveAttribute(attr, typedValue, true)
      return ContextCompat.getColor(context, typedValue.resourceId)
    }
  }

  @Parcelize
  data class Hex(private val color: String) : ColorRef() {
    override fun create(context: Context): Int = Color.parseColor(color)
  }

  @ColorInt
  abstract fun create(context: Context): Int

  open fun asColorStateList(context: Context): ColorStateList? = null
}

fun Int.toRawColorRef() = ColorRef.Raw(this)
fun Int.toResourceColorRef() = ColorRef.Resource(this)
