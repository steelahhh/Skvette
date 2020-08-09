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
data class CollectionResponse(
    val id: String,
    val title: String,
    @Json(name = "published_at")
    val publishedAt: String?,
    @Json(name = "last_collected_at")
    val lastCollectedAt: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "cover_photo")
    val coverPhoto: UrlSizesResponse?,
    val user: UserResponse?
)
