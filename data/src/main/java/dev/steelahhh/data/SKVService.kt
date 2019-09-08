/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data

import dev.steelahhh.data.photos.PhotoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SKVService {
    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): Single<List<PhotoResponse>>
}
