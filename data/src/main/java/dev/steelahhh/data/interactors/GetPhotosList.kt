/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.interactors

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.runCatching
import dev.steelahhh.core.AppCoroutineDispatchers
import dev.steelahhh.core.ResultInteractor
import dev.steelahhh.data.models.Order
import dev.steelahhh.data.models.PhotoPreviewUi
import dev.steelahhh.data.models.toPreviewUi
import dev.steelahhh.data.repositories.PhotosRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class GetPhotosList @Inject constructor(
  private val photosRepository: PhotosRepository,
  private val dispatchers: AppCoroutineDispatchers
) : ResultInteractor<GetPhotosList.Params, List<PhotoPreviewUi>>() {
  override val dispatcher: CoroutineDispatcher = dispatchers.io

  override suspend fun doWork(
    params: Params
  ): Result<List<PhotoPreviewUi>, Throwable> = runCatching<List<PhotoPreviewUi>> {
    photosRepository.getPhotos(params.page, params.order, ITEMS_PER_PAGE).map { it.toPreviewUi() }
  }

  data class Params(
    val page: Int,
    val order: Order = Order.LATEST
  )

  companion object {
    const val ITEMS_PER_PAGE = 20
  }
}
