/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.github.michaelbull.result.get
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dev.steelahhh.core.AppCoroutineDispatchers
import dev.steelahhh.core.Paginator
import dev.steelahhh.core.mvrx.fragment
import dev.steelahhh.data.interactors.GetPhotosList
import dev.steelahhh.data.models.Order
import dev.steelahhh.data.models.PhotoPreviewUi
import kotlin.math.abs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

data class PhotoListState(
  val photos: List<PhotoPreviewUi> = listOf(),
  val isError: Boolean = false,
  val isLoading: Boolean = false,
  val isRefreshing: Boolean = false,
  val isLoadingMore: Boolean = false
) : MavericksState

class PhotoListViewModel @AssistedInject constructor(
  @Assisted initialState: PhotoListState,
  private val getPhotosList: GetPhotosList,
  dispatchers: AppCoroutineDispatchers
) : MavericksViewModel<PhotoListState>(initialState), Paginator.ViewController<PhotoPreviewUi> {

  private val _order: MutableStateFlow<Order> = MutableStateFlow(Order.LATEST)

  private val paginator = Paginator(
    scope = viewModelScope + dispatchers.io,
    requestFactory = { page ->
      val (data, error) = getPhotosList(
        GetPhotosList.Params(
          page = page,
          order = _order.value
        )
      )
      if (error != null) throw error
      else data.orEmpty()
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

  override fun showData(data: List<PhotoPreviewUi>) = setState {
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

  companion object : MavericksViewModelFactory<PhotoListViewModel, PhotoListState> {
    override fun create(
      viewModelContext: ViewModelContext,
      state: PhotoListState
    ): PhotoListViewModel? {
      val fragment = viewModelContext.fragment<PhotoListFragment>()
      return fragment.component.photoListViewModelFactory.create(state)
    }
  }
}
