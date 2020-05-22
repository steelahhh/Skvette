/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.photos

import dagger.Reusable
import dev.steelahhh.core.AppCoroutineDispatchers
import dev.steelahhh.core.ProcessLifetime
import dev.steelahhh.data.Order
import dev.steelahhh.data.SKVService
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
        order: Order = Order.LATEST
    ): List<Photo> = withContext(scope.coroutineContext) {
        service.getPhotos(page, ITEMS_PER_PAGE, order.name).map { it.toDomain() }
    }

    companion object {
        const val ITEMS_PER_PAGE = 20
    }
}
