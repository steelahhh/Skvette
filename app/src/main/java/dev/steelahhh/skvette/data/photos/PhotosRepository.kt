/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette.data.photos

import dev.steelahhh.skvette.feature.photos.toDomain
import dev.steelahhh.skvette.network.SKVService
import io.github.steelahhh.core.SchedulerProvider
import io.github.steelahhh.core.rx.mapList
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val service: SKVService,
    private val schedulers: SchedulerProvider
) {
    fun getPhotos(page: Int, order: Order = Order.OLDEST) =
        service.getPhotos(page, ITEMS_PER_PAGE, order.name)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .mapList { it.toDomain() }

    companion object {
        const val ITEMS_PER_PAGE = 20
    }
}
