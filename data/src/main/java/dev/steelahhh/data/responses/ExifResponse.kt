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
data class ExifResponse(
  val make: String?,
  val model: String?,
  @Json(name = "exposure_time")
  val exposureTime: String?,
  val aperture: String?,
  @Json(name = "focal_length")
  val focalLength: String?,
  val iso: String?,
)
