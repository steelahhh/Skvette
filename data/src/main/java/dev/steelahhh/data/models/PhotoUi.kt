/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.models

import dev.steelahhh.core.ColorRef
import dev.steelahhh.data.responses.PhotoResponse
import java.text.NumberFormat

data class PhotoUi(
    val id: String,
    val colorRef: ColorRef,
    val url: String,
    val description: String?,
    val isLikedByUser: Boolean,
    val height: Int,
    val width: Int,
    val likes: Int,
    val views: Int,
    val exif: ExifUi?,
    val info: List<InfoItem>,
    val downloads: Int,
    val author: UserPreviewUi,
    val tags: List<String>
)

fun PhotoResponse.toUi(numberFormatter: NumberFormat) = PhotoUi(
    id = id,
    colorRef = ColorRef.Hex(color),
    height = height,
    width = width,
    description = description,
    isLikedByUser = likedByUser,
    exif = exif?.toUi(width, height),
    likes = likes ?: 0,
    views = views ?: 0,
    downloads = downloads ?: 0,
    info = listOf(
        InfoItem.VIEWS(numberFormatter.format((views ?: 0))),
        InfoItem.DOWNLOADS(numberFormatter.format((downloads ?: 0))),
        InfoItem.LIKES(numberFormatter.format((likes ?: 0)))
    ),
    url = urls.regular ?: "",
    author = user.toPreviewUi(),
    tags = tags?.map { it.title }.orEmpty()
)
