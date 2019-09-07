/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette.feature.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.fragmentViewModel
import dev.steelahhh.skvette.R
import dev.steelahhh.skvette.data.photos.PhotosRepository.Companion.ITEMS_PER_PAGE
import io.github.coreui.epoxy.loaderItem
import io.github.steelahhh.core.mvrx.BaseFragment
import io.github.steelahhh.core.mvrx.simpleController
import kotlinx.android.synthetic.main.fragment_photos.*
import kotlin.math.abs

class PhotosFragment : BaseFragment() {
    private val vm: PhotosViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photosRecycler.setController(epoxyController)
    }

    override fun epoxyController() = simpleController(vm) { state ->
        if (state.isRefreshing) {
            loaderItem {
                id("fullScreenLoader${state.photos.size}")
                isMatchParent(true)
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
    }
}
