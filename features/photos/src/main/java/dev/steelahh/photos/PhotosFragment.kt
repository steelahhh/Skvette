/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos

import android.os.Bundle
import android.view.View
import android.view.View.OVER_SCROLL_NEVER
import com.airbnb.mvrx.fragmentViewModel
import dev.steelahh.photos.di.DaggerPhotosComponent
import dev.steelahh.photos.di.PhotosComponent
import dev.steelahhh.core.mvrx.BaseFragment
import dev.steelahhh.core.mvrx.simpleController
import dev.steelahhh.coreui.epoxy.loaderItem
import dev.steelahhh.data.photos.PhotosRepository.Companion.ITEMS_PER_PAGE
import kotlin.math.abs
import kotlinx.android.synthetic.main.fragment_photos.*

class PhotosFragment : BaseFragment(R.layout.fragment_photos) {
    private val vm: PhotosViewModel by fragmentViewModel()

    val component: PhotosComponent by lazy { DaggerPhotosComponent.create() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photosRecycler.overScrollMode = OVER_SCROLL_NEVER
        photosRecycler.setController(epoxyController)
        // TODO consider the actual status height
        refresher.setProgressViewOffset(false, 75, 100)
        refresher.setOnRefreshListener { vm.refresh() }
    }

    override fun epoxyController() = simpleController(vm) { state ->
        if (state.isLoading) {
            loaderItem {
                id("fullScreenLoader${state.photos.size}")
                isMatchParent(true)
            }
        } else {
            headerItem {
                id("thisisaheader")
            }
        }

        state.photos.forEach {
            photoItem {
                id(it.id)
                photo(it)
                onBind { _, _, position ->
                    if (position > abs(state.photos.size - ITEMS_PER_PAGE / 2))
                        vm.loadMore()
                }
            }
        }

        if (state.isLoadingMore) {
            loaderItem {
                id("loaderItem${state.photos.size}")
                isMatchParent(false)
            }
        }
        refresher.isEnabled = !state.isLoading
        refresher.isRefreshing = state.isRefreshing
    }
}