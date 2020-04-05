/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.photos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoResponse(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val width: Int,
    val height: Int,
    val color: String,
    val description: String?,
    val categories: List<Any>,
    val urls: UrlSizesResponse,
    val links: LinksResponse,
    val liked_by_user: Boolean,
    val sponsored: Boolean?,
    val likes: Int,
    val user: UserResponse,
    val current_user_collections: List<Any>
)

@JsonClass(generateAdapter = true)
data class UserResponse(
    val id: String,
    val updated_at: String,
    val username: String,
    val name: String,
    val first_name: String,
    val last_name: String?,
    val twitter_username: String?,
    val portfolio_url: String?,
    val bio: String?,
    val location: String?,
    val links: LinksResponse,
    val profile_image: UrlSizesResponse,
    val total_collections: Int?,
    val instagram_username: String?,
    val total_likes: Int?,
    val total_photos: Int?
)

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
    val download_location: String?
)

@JsonClass(generateAdapter = true)
data class UrlSizesResponse(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val medium: String?,
    val large: String?,
    val thumb: String?
)
