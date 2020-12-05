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
