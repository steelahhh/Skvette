/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.photos

data class Photo(
    val id: String,
    val color: String,
    val width: Int,
    val height: Int,
    val url: String
)

fun PhotoResponse.toDomain() = Photo(
    id = id,
    color = color,
    width = width,
    height = height,
    url = urls.regular ?: ""
)
