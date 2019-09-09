/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.photos

import dagger.Reusable
import dev.steelahhh.data.Order
import dev.steelahhh.data.SKVService
import dev.steelahhh.core.SchedulerProvider
import dev.steelahhh.core.rx.mapList
import javax.inject.Inject

@Reusable
class PhotosRepository @Inject constructor(
    private val service: SKVService,
    private val schedulers: SchedulerProvider
) {
    fun getPhotos(page: Int, order: Order = Order.LATEST) =
        service.getPhotos(page, ITEMS_PER_PAGE, order.name)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .mapList { it.toDomain() }

    companion object {
        const val ITEMS_PER_PAGE = 20
    }
}
