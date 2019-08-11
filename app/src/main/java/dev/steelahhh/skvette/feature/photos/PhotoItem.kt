/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette.feature.photos

import android.graphics.Color
import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import dev.steelahhh.skvette.R
import io.github.coreui.epoxy.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_photo)
abstract class PhotoItem : EpoxyModelWithHolder<PhotoItem.Holder>() {

    @EpoxyAttribute
    lateinit var photo: Photo

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(holder) {
            image.setBackgroundColor(Color.parseColor(photo.color))

            Glide.with(image)
                .load(photo.url)
                .apply(RequestOptions().centerCrop())
                .into(image)
        }
    }

    class Holder : KotlinEpoxyHolder() {
        val image by bind<ImageView>(R.id.itemPhotoImageView)
        val container by bind<MaterialCardView>(R.id.itemPhotoContainer)
    }
}
