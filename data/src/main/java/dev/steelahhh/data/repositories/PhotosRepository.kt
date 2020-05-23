/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.repositories

import dagger.Reusable
import dev.steelahhh.core.AppCoroutineDispatchers
import dev.steelahhh.core.ProcessLifetime
import dev.steelahhh.data.models.Order
import dev.steelahhh.data.SKVService
import dev.steelahhh.data.responses.PhotoResponse
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext

@Reusable
class PhotosRepository @Inject constructor(
    private val service: SKVService,
    dispatchers: AppCoroutineDispatchers,
    @ProcessLifetime val processScope: CoroutineScope
) {
    private val scope: CoroutineScope = processScope + dispatchers.io

    suspend fun getPhotos(
        page: Int,
        order: Order,
        itemsPerPage: Int
    ): List<PhotoResponse> = withContext(scope.coroutineContext) {
        service.getPhotos(page, itemsPerPage, order.name)
    }

}
