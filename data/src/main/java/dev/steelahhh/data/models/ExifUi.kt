/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.models

import dev.steelahhh.data.models.ExifUi.Companion.INVALID_VALUE
import dev.steelahhh.data.responses.ExifResponse

data class ExifUi(
    val camera: String,
    val focalLength: String,
    val ISO: String,
    val aperture: String,
    val shutterSpeed: String,
    val dimensions: String,
) {
    companion object {
        const val INVALID_VALUE = "unknown"
    }
}

fun ExifResponse.toUi(width: Int, height: Int) = ExifUi(
    camera = model ?: INVALID_VALUE,
    focalLength = focalLength?.let { "${it}mm" } ?: INVALID_VALUE,
    ISO = iso ?: INVALID_VALUE,
    aperture = aperture?.let { "ƒ/$it" } ?: INVALID_VALUE,
    shutterSpeed = exposureTime?.let { "${it}s " } ?: INVALID_VALUE,
    dimensions = listOf(width, height)
        .joinToString(separator = " x ")
        .takeIf { it.isNotEmpty() } ?: INVALID_VALUE
)
