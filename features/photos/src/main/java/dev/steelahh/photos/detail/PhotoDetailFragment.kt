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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.MvRx
import com.airbnb.mvrx.args
import com.airbnb.mvrx.fragmentViewModel
import com.zhuinden.simplestack.navigator.Navigator
import dev.steelahhh.core.navigation.FullScreen
import dev.steelahhh.core.navigation.ScreenKey
import dev.steelahhh.coreui.extensions.withArguments
import kotlinx.android.parcel.Parcelize

class PhotoDetailFragment : Fragment(), MavericksView {
    private val vm by fragmentViewModel<PhotoDetailFragment, PhotoDetailViewModel, PhotoDetailState>()
    private val args: Arguments by args()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FrameLayout(requireContext()).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        photoDetailUi(
            viewGroup = this,
            actioner = {
                when (it) {
                    PhotoDetailAction.GoBack -> Navigator.getBackstack(requireContext()).goBack()
                }
            },
            photoUrl = args.url
        )
    }

    @Parcelize
    data class Arguments(
        val id: Long = -1,
        @Deprecated("Pass only valid id")
        val url: String = "",
        @Deprecated("Should be taken from PhotoResponse")
        val color: String = ""
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

    override fun invalidate() {
    }
}
