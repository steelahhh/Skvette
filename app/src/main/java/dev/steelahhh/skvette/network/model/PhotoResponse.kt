/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette.network.model

import com.squareup.moshi.Json

data class PhotoResponse(
    @Json(name = "id") val id: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int,
    @Json(name = "color") val color: String,
    @Json(name = "description") val description: String,
    @Json(name = "categories") val categories: List<Any>,
    @Json(name = "urls") val urls: UrlSizesResponse,
    @Json(name = "links") val links: LinksResponse,
    @Json(name = "liked_by_user") val likedByUser: Boolean,
    @Json(name = "sponsored") val sponsored: Boolean,
    @Json(name = "likes") val likes: Int,
    @Json(name = "user") val user: UserResponse,
    @Json(name = "current_user_collections") val currentUserCollections: List<Any>
)

data class UserResponse(
    @Json(name = "id") val id: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "username") val username: String,
    @Json(name = "name") val name: String,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "twitter_username") val twitterUsername: String,
    @Json(name = "portfolio_url") val portfolioUrl: String,
    @Json(name = "bio") val bio: String,
    @Json(name = "location") val location: String,
    @Json(name = "links") val links: LinksResponse,
    @Json(name = "profile_image") val profileImage: UrlSizesResponse,
    @Json(name = "total_collections") val totalCollections: Int,
    @Json(name = "instagram_username") val instagramUsername: String,
    @Json(name = "total_likes") val totalLikes: Int,
    @Json(name = "total_photos") val totalPhotos: Int
)

data class LinksResponse(
    @Json(name = "self") val self: String?,
    @Json(name = "html") val html: String?,
    @Json(name = "photos") val photos: String?,
    @Json(name = "likes") val likes: String?,
    @Json(name = "portfolio") val portfolio: String?,
    @Json(name = "following") val following: String?,
    @Json(name = "followers") val followers: String?,
    @Json(name = "download") val download: String?,
    @Json(name = "download_location") val downloadLocation: String?
)

data class UrlSizesResponse(
    @Json(name = "raw") val raw: String?,
    @Json(name = "full") val full: String?,
    @Json(name = "regular") val regular: String?,
    @Json(name = "small") val small: String?,
    @Json(name = "medium") val medium: String?,
    @Json(name = "large") val large: String?,
    @Json(name = "thumb") val thumb: String?
)
