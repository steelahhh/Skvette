/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.MvRx
import com.airbnb.mvrx.args
import com.airbnb.mvrx.fragmentViewModel
import com.zhuinden.simplestack.navigator.Navigator
import dev.steelahh.photos.di.DaggerPhotosComponent
import dev.steelahh.photos.di.PhotosComponent
import dev.steelahhh.core.ColorRef
import dev.steelahhh.core.navigation.FullScreen
import dev.steelahhh.core.navigation.ScreenKey
import dev.steelahhh.coreui.extensions.tryToOpenUrl
import dev.steelahhh.coreui.extensions.tryToShare
import dev.steelahhh.coreui.extensions.withArguments
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach

class PhotoDetailFragment : Fragment(), MavericksView {
  private val vm by fragmentViewModel<PhotoDetailFragment, PhotoDetailViewModel, PhotoDetailState>()
  private val args: Arguments by args()

  internal val component: PhotosComponent by lazy { DaggerPhotosComponent.create() }

  @ExperimentalLayout
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = FrameLayout(requireContext()).apply {
    layoutParams = ViewGroup.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT
    )

    background = ColorDrawable(Color.TRANSPARENT)

    photoDetailUi(
      viewGroup = this,
      stateFlow = vm.stateFlow.onEach {
        if (!it.isLoading && it.photo != null)
          delay(300L)
      },
      actioner = { action ->
        when (action) {
          is PhotoDetailAction.GoBack -> Navigator.getBackstack(requireContext()).goBack()
          is PhotoDetailAction.OpenInBrowser -> tryToOpenUrl(action.unsplashUrl)
          is PhotoDetailAction.Share -> {
            action.unsplashUrl?.let(::tryToShare)
          }
          else -> vm.handleAction(action)
        }
      },
      photoUrl = args.url,
      photoColor = ColorRef.Hex(args.color)
    )
  }

  @Parcelize
  data class Arguments(
    val id: String,
    val url: String,
    val color: String,
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

  override fun invalidate() = Unit
}
