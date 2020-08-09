/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationResponse(
    val city: String,
    val country: String,
    val position: LatLngResponse
) {

    @JsonClass(generateAdapter = true)
    data class LatLngResponse(
        val latitude: Double,
        val longitude: Double
    )
}
