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
import com.github.michaelbull.result.getOr
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dev.steelahhh.core.AppCoroutineDispatchers
import dev.steelahhh.core.Paginator
import dev.steelahhh.core.mvrx.MvRxViewModel
import dev.steelahhh.core.mvrx.fragment
import dev.steelahhh.data.interactors.GetPhotosList
import dev.steelahhh.data.models.Order
import dev.steelahhh.data.models.Photo
import kotlin.math.abs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

data class PhotoListState(
    val photos: List<Photo> = listOf(),
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLoadingMore: Boolean = false
) : MvRxState

class PhotoListViewModel @AssistedInject constructor(
    @Assisted initialState: PhotoListState,
    private val getPhotosList: GetPhotosList,
    dispatchers: AppCoroutineDispatchers
) : MvRxViewModel<PhotoListState>(initialState), Paginator.ViewController<Photo> {

    private val _order: MutableStateFlow<Order> = MutableStateFlow(
        Order.LATEST)

    private val paginator = Paginator(
        scope = viewModelScope + dispatchers.io,
        requestFactory = { page ->
            getPhotosList(
                GetPhotosList.Params(
                    page = page,
                    order = _order.value
                )
            ).getOr { emptyList() }
        },
        viewController = this
    )

    init {
        /**
         * Whenever [_order] changes, restart the paginator
         */
        viewModelScope.launch {
            _order.collect {
                paginator.restart()
            }
        }
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

    fun loadMore(position: Int) = withState { state ->
        if (position > abs(state.photos.size - GetPhotosList.ITEMS_PER_PAGE / 2))
            paginator.loadNewPage()
    }

    fun refresh() = paginator.refresh()

    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: PhotoListState): PhotoListViewModel
    }

    companion object : MvRxViewModelFactory<PhotoListViewModel, PhotoListState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: PhotoListState
        ): PhotoListViewModel? {
            val fragment = viewModelContext.fragment<PhotoListFragment>()
            return fragment.component.photoListViewModelFactory.create(state)
        }
    }
}
