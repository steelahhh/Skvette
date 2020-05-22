/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos

import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dev.steelahhh.core.AppCoroutineDispatchers
import dev.steelahhh.core.Paginator
import dev.steelahhh.core.mvrx.MvRxViewModel
import dev.steelahhh.core.mvrx.fragment
import dev.steelahhh.data.photos.Photo
import dev.steelahhh.data.photos.PhotosRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.plus

data class PhotosState(
    val photos: List<Photo> = listOf(),
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLoadingMore: Boolean = false
) : MvRxState

class PhotosViewModel @AssistedInject constructor(
    @Assisted initialState: PhotosState,
    private val photosRepository: PhotosRepository,
    dispatchers: AppCoroutineDispatchers
) : MvRxViewModel<PhotosState>(initialState), Paginator.ViewController<Photo> {

    private val paginator = Paginator(
        scope = viewModelScope + dispatchers.io,
        requestFactory = { page -> photosRepository.getPhotos(page) },
        viewController = this
    )

    init {
        paginator.restart()
    }

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

    override fun showEmptyError(error: Throwable) = setState {
        copy(isError = true)
    }

    override fun showData(data: List<Photo>) = setState {
        copy(photos = data, isLoadingMore = false, isRefreshing = false, isError = false)
    }

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
