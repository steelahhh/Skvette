/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.Navigator
import dev.steelahh.photos.PhotoListFragment
import dev.steelahhh.core.ColorRef
import dev.steelahhh.core.DrawableRef
import dev.steelahhh.core.NavBarScrollController
import dev.steelahhh.core.NavBarScrollController.ScrollDirection
import dev.steelahhh.core.navigation.FullScreen
import dev.steelahhh.core.navigation.ScreenKey
import dev.steelahhh.core.statusbar.StatusBarController
import dev.steelahhh.coreui.extensions.dp
import dev.steelahhh.coreui.views.navigation.FloatingNavigationItem
import dev.steelahhh.skvette.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity(), StateChanger {
    private lateinit var fragmentStateChanger: FragmentStateChanger
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentStateChanger = FragmentStateChanger(supportFragmentManager, binding.container.id)

        Navigator.configure()
            .setStateChanger(this)
            .install(this, binding.container, History.single(PhotoListFragment.Key()))

        lifecycleScope.launchWhenCreated {
            StatusBarController.flow().collect { configuration ->
                binding.root.updateStatusBar(
                    height = configuration.height,
                    colorRef = configuration.colorRef,
                    visible = configuration.visible
                )
            }
        }

        binding.floatingNavigationView.spacing = 16.dp

        binding.floatingNavigationView.items = listOf(
            FloatingNavigationItem.Icon(
                _isActive = true,
                icon = DrawableRef.Resource(R.drawable.ic_photo),
                iconTint = ColorRef.Raw(Color.DKGRAY),
                selectionColor = ColorRef.Attribute(R.attr.colorOnSurface)
            ),
            FloatingNavigationItem.Icon(
                _isActive = false,
                icon = DrawableRef.Resource(R.drawable.ic_collections),
                iconTint = ColorRef.Raw(Color.DKGRAY),
                selectionColor = ColorRef.Attribute(R.attr.colorOnSurface)
            )
        )

        lifecycleScope.launchWhenResumed {
            NavBarScrollController.flow().collect { status ->
                when (status) {
                    ScrollDirection.UP -> binding.floatingNavigationView.show()
                    ScrollDirection.DOWN -> binding.floatingNavigationView.hide()
                    ScrollDirection.IDLE -> binding.floatingNavigationView.isVisible = true
                }
            }
        }
    }

    @Override
    override fun onBackPressed() {
        if (!Navigator.onBackPressed(this)) super.onBackPressed()
    }

    override fun handleStateChange(stateChange: StateChange, completionCallback: StateChanger.Callback) {
        if (stateChange.isTopNewKeyEqualToPrevious) {
            completionCallback.stateChangeComplete()
            return
        }
        fragmentStateChanger.handleStateChange(stateChange)
        completionCallback.stateChangeComplete()
        lifecycleScope.launchWhenResumed {
            val isCurrentFullScreen = stateChange.topNewKey<ScreenKey>() is FullScreen
            binding.floatingNavigationView.isGone = isCurrentFullScreen
        }
    }
}
