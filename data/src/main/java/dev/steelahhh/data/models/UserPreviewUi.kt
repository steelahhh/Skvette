/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.models

import dev.steelahhh.data.responses.UrlSizesResponse
import dev.steelahhh.data.responses.UserResponse

data class UserPreviewUi(
    val id: String,
    val name: String,
    val profileImage: UrlSizesResponse,
    val totalPhotos: Int,
    val totalCollections: Int
)

fun UserResponse.toPreviewUi() = UserPreviewUi(
    id = id,
    name = listOfNotNull(firstName, lastName).joinToString(" "),
    profileImage = profileImage,
    totalPhotos = totalPhotos ?: 1,
    totalCollections = totalCollections ?: 0
)
