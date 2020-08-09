/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksResponse(
    val self: String?,
    val html: String?,
    val photos: String?,
    val likes: String?,
    val portfolio: String?,
    val following: String?,
    val followers: String?,
    val download: String?,
    @Json(name = "download_location")
    val downloadLocation: String?
)
