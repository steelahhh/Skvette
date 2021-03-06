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
  val exif: ExifResponse?,
  val position: LocationResponse.LatLngResponse?,
  val tags: List<TagResponse>?,
  val categories: List<Any>,
  val urls: UrlSizesResponse,
  val links: LinksResponse,
  @Json(name = "liked_by_user")
  val likedByUser: Boolean,
  val sponsored: Boolean?,
  val likes: Int?,
  val views: Int?,
  val downloads: Int?,
  val user: UserResponse,
  @Json(name = "current_user_collections")
  val currentUserCollections: List<CollectionResponse>
)
