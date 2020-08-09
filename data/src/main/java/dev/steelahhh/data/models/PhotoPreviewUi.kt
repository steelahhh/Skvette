/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.models

import dev.steelahhh.data.responses.PhotoResponse

data class PhotoPreviewUi(
    val id: String,
    val color: String,
    val width: Int,
    val height: Int,
    val url: String
)

// TODO: control the url size from settings
fun PhotoResponse.toPreviewUi() = PhotoPreviewUi(
    id = id,
    color = color,
    width = width,
    height = height,
    url = urls.regular ?: ""
)
