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
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import com.airbnb.mvrx.fragmentViewModel
import dev.steelahh.photos.databinding.FragmentPhotosBinding
import dev.steelahh.photos.di.DaggerPhotosComponent
import dev.steelahh.photos.di.PhotosComponent
import dev.steelahhh.core.mvrx.BaseFragment
import dev.steelahhh.core.mvrx.simpleController
import dev.steelahhh.core.statusbar.StatusBarController
import dev.steelahhh.coreui.epoxy.loaderItem
import dev.steelahhh.coreui.viewBinding
import dev.steelahhh.data.photos.PhotosRepository.Companion.ITEMS_PER_PAGE
import kotlin.math.abs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@Suppress("EXPERIMENTAL_API_USAGE")
class PhotosFragment : BaseFragment(R.layout.fragment_photos) {
    private val vm: PhotosViewModel by fragmentViewModel()
    private val binding: FragmentPhotosBinding by viewBinding(FragmentPhotosBinding::bind)

    val component: PhotosComponent by lazy { DaggerPhotosComponent.create() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.photosRecycler.overScrollMode = OVER_SCROLL_NEVER
        binding.photosRecycler.setController(epoxyController)
        binding.refresher.setOnRefreshListener { vm.refresh() }
        // TODO: this feels hacky and StatusBarController might even be unnecessary,
        //  revisit when adding more screens
        lifecycleScope.launchWhenCreated {
            StatusBarController.flow().collect {
                if (it.height != 0) {
                    binding.refresher.setProgressViewOffset(
                        true,
                        StatusBarController.height,
                        StatusBarController.height + 44
                    )
                    binding.photosRecycler.updatePadding(top = it.height)
                }
            }
        }
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
        lifecycleScope.launchWhenResumed {
            binding.refresher.isEnabled = !state.isLoading
            binding.refresher.isRefreshing = state.isRefreshing
        }
    }
}
