/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dev.steelahhh.core.AppCoroutineDispatchers
import dev.steelahhh.core.mvrx.MvRxViewModel
import dev.steelahhh.core.mvrx.fragment
import dev.steelahhh.data.interactors.GetPhoto
import dev.steelahhh.data.models.PhotoUi

data class PhotoDetailState(
  val placeholder: String = "",
  val isLoading: Boolean = true,
  val photo: PhotoUi? = null,
  val error: String? = null,
) : MvRxState

class PhotoDetailViewModel @AssistedInject constructor(
  @Assisted initialState: PhotoDetailState,
  @Assisted private val photoId: String,
  private val dispatchers: AppCoroutineDispatchers,
  private val getPhoto: GetPhoto,
) : MvRxViewModel<PhotoDetailState>(initialState) {

  init {
    loadPhoto()
  }

  fun handleAction(action: PhotoDetailAction) {
    when (action) {
      is PhotoDetailAction.Refresh -> loadPhoto()
      is PhotoDetailAction.OpenUser -> TODO()
      is PhotoDetailAction.OpenTag -> TODO()
      is PhotoDetailAction.Download -> TODO()
      is PhotoDetailAction.Like -> TODO()
      is PhotoDetailAction.AddToCollection -> TODO()
      is PhotoDetailAction.SetAsWallpaper -> TODO()
      else -> Unit // action was handled in fragment
    }
  }

  private fun loadPhoto() {
    suspend { getPhoto(GetPhoto.Params(photoId)) }.execute(dispatchers.io) {
      val result = it()
      copy(isLoading = it is Loading, photo = result?.get(), error = result?.getError()?.message)
    }
  }

  @AssistedInject.Factory
  interface Factory {
    fun create(
      initialState: PhotoDetailState,
      photoId: String
    ): PhotoDetailViewModel
  }

  companion object : MvRxViewModelFactory<PhotoDetailViewModel, PhotoDetailState> {
    override fun initialState(viewModelContext: ViewModelContext): PhotoDetailState? {
      val args = viewModelContext.args<PhotoDetailFragment.Arguments>()
      return PhotoDetailState(placeholder = args.url)
    }

    override fun create(
      viewModelContext: ViewModelContext,
      state: PhotoDetailState
    ): PhotoDetailViewModel? {
      val args = viewModelContext.args<PhotoDetailFragment.Arguments>()
      val fragment = viewModelContext.fragment<PhotoDetailFragment>()
      return fragment.component.photoDetailViewModelFactory.create(state, args.id)
    }
  }
}
