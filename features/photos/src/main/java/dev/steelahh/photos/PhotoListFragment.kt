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
import dev.steelahhh.core.attachNavBarController
import dev.steelahhh.core.mvrx.BaseFragment
import dev.steelahhh.core.mvrx.simpleController
import dev.steelahhh.core.navigation.ScreenKey
import dev.steelahhh.coreui.extensions.viewBinding
import dev.steelahhh.coreui.views.loaderItemView
import dev.steelahhh.data.models.Photo
import kotlinx.android.parcel.Parcelize

class PhotoListFragment : BaseFragment(R.layout.fragment_photos) {
    private val vm by fragmentViewModel<PhotoListFragment, PhotoListViewModel, PhotoListState>()
    private val binding: FragmentPhotosBinding by viewBinding(FragmentPhotosBinding::bind)

    internal val component: PhotosComponent by lazy { DaggerPhotosComponent.create() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.photosRecycler.run {
            overScrollMode = OVER_SCROLL_NEVER
            setController(epoxyController)
            attachNavBarController()
        }
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
                onBind { _, _, position -> vm.loadMore(position) }
                onClick { photo, _ -> photo.navigate() }
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

    private fun Photo.navigate() {
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
        fun newInstance() = PhotoListFragment()
    }
}
