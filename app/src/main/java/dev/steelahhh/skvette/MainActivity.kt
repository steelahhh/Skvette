/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.skvette

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.Navigator
import dev.steelahh.photos.PhotoListFragment
import dev.steelahhh.core.statusbar.StatusBarController
import dev.steelahhh.skvette.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(), StateChanger {
    private lateinit var fragmentStateChanger: FragmentStateChanger

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentStateChanger = FragmentStateChanger(supportFragmentManager, binding.container.id)

        Navigator.configure()
            .setStateChanger(this)
            .install(this, binding.container, History.single(PhotoListFragment.Key()))

        lifecycleScope.launchWhenCreated {
            StatusBarController.flow().collect { configuration ->
                binding.root.updateStatusBar(configuration.height, configuration.visible)
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
    }
}
