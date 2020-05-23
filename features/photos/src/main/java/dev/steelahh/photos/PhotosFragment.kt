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
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.airbnb.mvrx.fragmentViewModel
import com.zhuinden.simplestack.navigator.Navigator
import dev.steelahh.photos.databinding.FragmentPhotosBinding
import dev.steelahh.photos.detail.PhotoDetailFragment
import dev.steelahh.photos.di.DaggerPhotosComponent
import dev.steelahh.photos.di.PhotosComponent
import dev.steelahh.photos.views.photoListItemView
import dev.steelahhh.core.mvrx.BaseFragment
import dev.steelahhh.core.mvrx.simpleController
import dev.steelahhh.core.navigation.ScreenKey
import dev.steelahhh.coreui.extensions.viewBinding
import dev.steelahhh.coreui.views.loaderItemView
import dev.steelahhh.data.photos.Photo
import dev.steelahhh.data.photos.PhotosRepository.Companion.ITEMS_PER_PAGE
import kotlin.math.abs
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class PhotosFragment : BaseFragment(R.layout.fragment_photos) {
    private val vm: PhotosViewModel by fragmentViewModel()
    private val binding: FragmentPhotosBinding by viewBinding(FragmentPhotosBinding::bind)

    internal val component: PhotosComponent by lazy { DaggerPhotosComponent.create() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.photosRecycler.overScrollMode = OVER_SCROLL_NEVER
        binding.photosRecycler.setController(epoxyController)
        binding.refresher.setOnRefreshListener { vm.refresh() }
        binding.root.doOnLayout {
            binding.toolbar.padToStatus = true
        }
    }

    override fun epoxyController() = simpleController(vm) { state ->
        if (state.isLoading) {
            loaderItemView {
                id("fullScreenLoader${state.photos.size}")
                isMatchParent(true)
            }
        }

        state.photos.forEach {
            photoListItemView {
                id(it.id)
                photo(it)
                onBind { _, _, position ->
                    if (position > abs(state.photos.size - ITEMS_PER_PAGE / 2))
                        vm.loadMore()
                }
                onClick { photo, view ->
                    photo.navigate(view)
                }
            }
        }

        if (state.isLoadingMore) {
            loaderItemView {
                id("loaderItem${state.photos.size}")
                isMatchParent(false)
            }
        }
        lifecycleScope.launchWhenResumed {
            binding.refresher.isEnabled = !state.isLoading
            binding.refresher.isRefreshing = state.isRefreshing
        }
    }

    private fun Photo.navigate(view: View) {
        Navigator.getBackstack(requireContext()).goTo(
            PhotoDetailFragment.Key(
                PhotoDetailFragment.Arguments(
                    url = url
                )
            )
        )
    }

    @Parcelize
    data class Key(private val placeholder: String = "") : ScreenKey() {
        override fun createFragment(): Fragment = newInstance()
    }

    companion object {
        fun newInstance() = PhotosFragment()
    }
}
