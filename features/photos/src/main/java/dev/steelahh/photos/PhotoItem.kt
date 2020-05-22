/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos

import android.graphics.Color
import android.widget.ImageView
import coil.annotation.ExperimentalCoilApi
import coil.api.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.card.MaterialCardView
import dev.steelahhh.coreui.epoxy.KotlinEpoxyHolder
import dev.steelahhh.data.photos.Photo

@ExperimentalCoilApi
@EpoxyModelClass
abstract class PhotoItem : EpoxyModelWithHolder<PhotoItem.Holder>() {

    @EpoxyAttribute
    lateinit var photo: Photo

    override fun getDefaultLayout(): Int = R.layout.item_photo

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(holder) {
            image.setBackgroundColor(Color.parseColor(photo.color))
            image.contentDescription = photo.id
            image.load(photo.url) {
                crossfade(true)
            }
        }
    }

    class Holder : KotlinEpoxyHolder() {
        val image by bind<ImageView>(R.id.itemPhotoImageView)
        val container by bind<MaterialCardView>(R.id.itemPhotoContainer)
    }
}
