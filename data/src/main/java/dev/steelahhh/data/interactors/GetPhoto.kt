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
import dev.steelahhh.data.models.PhotoUi
import dev.steelahhh.data.models.toUi
import dev.steelahhh.data.repositories.PhotosRepository
import java.text.NumberFormat
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class GetPhoto @Inject constructor(
  private val photosRepository: PhotosRepository,
  private val dispatchers: AppCoroutineDispatchers
) : ResultInteractor<GetPhoto.Params, PhotoUi>() {
  override val dispatcher: CoroutineDispatcher = dispatchers.io

  override suspend fun doWork(params: Params): Result<PhotoUi, Throwable> = runCatching {
    photosRepository.getPhoto(params.id).toUi(NumberFormat.getInstance())
  }

  data class Params(
    val id: String
  )
}
