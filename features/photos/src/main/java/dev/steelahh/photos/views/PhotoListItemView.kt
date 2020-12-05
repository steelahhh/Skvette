/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import coil.load
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.google.android.material.card.MaterialCardView
import dev.steelahh.photos.R
import dev.steelahhh.data.models.PhotoPreviewUi
import javax.annotation.Nullable

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class PhotoListItemView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  private val imageView: ImageView
  private val container: MaterialCardView

  init {
    View.inflate(context, R.layout.item_photo, this)
    imageView = findViewById(R.id.itemPhotoImageView)
    container = findViewById(R.id.itemPhotoContainer)
  }

  var photo: PhotoPreviewUi? = null @ModelProp set

  @Nullable
  var onClick: ((photo: PhotoPreviewUi, view: View) -> Unit)? = null
    @CallbackProp set

  @AfterPropsSet
  fun afterPropsSet() {
    val photo = photo ?: return
    val onClick = onClick ?: return
    imageView.run {
      setBackgroundColor(Color.parseColor(photo.color))
      contentDescription = photo.id
      load(photo.url) {
        crossfade(true)
      }
    }
    container.setOnClickListener {
      onClick(photo, imageView)
    }
  }
}
