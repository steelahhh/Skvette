/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette.feature.photos

import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import dev.steelahhh.skvette.data.photos.PhotosRepository
import dev.steelahhh.skvette.di.injector
import io.github.steelahhh.core.Paginator
import io.github.steelahhh.core.mvrx.MvRxViewModel
import io.reactivex.Single

data class PhotosState(
    val photos: List<Photo> = listOf(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLoadingMore: Boolean = false
) : MvRxState

class PhotosViewModel(
    initialState: PhotosState,
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

    companion object : MvRxViewModelFactory<PhotosViewModel, PhotosState> {

        override fun create(
            viewModelContext: ViewModelContext,
            state: PhotosState
        ): PhotosViewModel? {
            val repo = viewModelContext.activity.injector.photosRepository
            return PhotosViewModel(PhotosState(), repo)
        }
    }
}
