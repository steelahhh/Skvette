/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos

import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dev.steelahhh.core.Paginator
import dev.steelahhh.core.mvrx.MvRxViewModel
import dev.steelahhh.core.mvrx.fragment
import dev.steelahhh.data.photos.Photo
import dev.steelahhh.data.photos.PhotosRepository
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi

data class PhotosState(
    val photos: List<Photo> = listOf(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLoadingMore: Boolean = false
) : MvRxState

class PhotosViewModel @AssistedInject constructor(
    @Assisted initialState: PhotosState,
    private val photosRepository: PhotosRepository
) : MvRxViewModel<PhotosState>(initialState) {

    private val paginator = Paginator(
        ::fetchPhotos,
        object : Paginator.EmptyViewController<Photo>() {
            override fun showPageLoadingProgress() = setState {
                copy(isLoadingMore = true)
            }

            override fun hidePageLoadingProgress() = setState {
                copy(isLoadingMore = false)
            }

            override fun showEmptyProgress() = setState {
                copy(isLoading = true)
            }

            override fun hideEmptyProgress() = setState {
                copy(isLoading = false)
            }

            override fun showRefreshProgress() = setState {
                copy(isRefreshing = true)
            }

            override fun hideRefreshProgress() = setState {
                copy(isRefreshing = false)
            }

            override fun showData(data: List<Photo>) = setState {
                copy(photos = data, isLoadingMore = false, isRefreshing = false)
            }
        }
    )

    init {
        paginator.restart()
    }

    private fun fetchPhotos(page: Int): Single<List<Photo>> = photosRepository.getPhotos(page)

    fun loadMore() = paginator.loadNewPage()

    fun refresh() = paginator.refresh()

    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: PhotosState): PhotosViewModel
    }

    companion object : MvRxViewModelFactory<PhotosViewModel, PhotosState> {
        @ExperimentalCoroutinesApi
        override fun create(
            viewModelContext: ViewModelContext,
            state: PhotosState
        ): PhotosViewModel? {
            val fragment = viewModelContext.fragment<PhotosFragment>()
            return fragment.component.photosViewModelFactory.create(state)
        }
    }
}
