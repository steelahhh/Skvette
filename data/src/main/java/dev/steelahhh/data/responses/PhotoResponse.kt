/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoResponse(
    val id: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    val width: Int,
    val height: Int,
    val color: String,
    val description: String?,
    val categories: List<Any>,
    val urls: UrlSizesResponse,
    val links: LinksResponse,
    @Json(name = "liked_by_user")
    val likedByUser: Boolean,
    val sponsored: Boolean?,
    val likes: Int,
    val user: UserResponse,
    @Json(name = "current_user_collections")
    val currentUserCollections: List<Any>
)

@JsonClass(generateAdapter = true)
data class UserResponse(
    val id: String,
    val updated_at: String,
    val username: String,
    val name: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String?,
    @Json(name = "twitter_username")
    val twitterUsername: String?,
    @Json(name = "portfolio_url")
    val portfolioUrl: String?,
    val bio: String?,
    val location: String?,
    val links: LinksResponse,
    @Json(name = "profile_image")
    val profileImage: UrlSizesResponse,
    @Json(name = "total_collections")
    val totalCollections: Int?,
    @Json(name = "instagram_username")
    val instagramUsername: String?,
    @Json(name = "total_likes")
    val totalLikes: Int?,
    @Json(name = "total_photos")
    val totalPhotos: Int?
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
    @Json(name = "download_location")
    val downloadLocation: String?
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

@JsonClass(generateAdapter = true)
data class CollectionResponse(
    val id: String,
    val title: String,
    @Json(name = "published_at")
    val publishedAt: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "cover_photo")
    val coverPhoto: UrlSizesResponse?,
    val user: UserResponse?

)
