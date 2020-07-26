/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.airbnb.mvrx.MvRx
import com.airbnb.mvrx.args
import com.airbnb.mvrx.fragmentViewModel
import dev.steelahh.photos.R
import dev.steelahh.photos.databinding.FragmentPhotoDetailBinding
import dev.steelahhh.core.mvrx.BaseFragment
import dev.steelahhh.core.mvrx.MvRxEpoxyController
import dev.steelahhh.core.mvrx.simpleController
import dev.steelahhh.core.navigation.FullScreen
import dev.steelahhh.core.navigation.ScreenKey
import dev.steelahhh.coreui.extensions.viewBinding
import dev.steelahhh.coreui.extensions.withArguments
import kotlinx.android.parcel.Parcelize

class PhotoDetailFragment : BaseFragment(R.layout.fragment_photo_detail) {
    private val vm by fragmentViewModel<PhotoDetailFragment, PhotoDetailViewModel, PhotoDetailState>()
    private val args: Arguments by args()

    private val binding: FragmentPhotoDetailBinding by viewBinding(FragmentPhotoDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            binding.bigImageIv.load(args.url) {
                crossfade(true)
                listener { _, _ ->
                    startPostponedEnterTransition()
                }
            }
        }
    }

    override fun epoxyController(): MvRxEpoxyController = simpleController {
    }

    @Parcelize
    data class Arguments(
        val id: Long = -1,
        @Deprecated("Pass only valid id")
        val url: String = ""
    ) : Parcelable

    @Parcelize
    data class Key(val args: Arguments) : ScreenKey(), FullScreen {
        override fun createFragment(): Fragment = newInstance(args)
    }

    companion object {
        fun newInstance(args: Arguments) = PhotoDetailFragment().withArguments {
            putParcelable(MvRx.KEY_ARG, args)
        }
    }
}
