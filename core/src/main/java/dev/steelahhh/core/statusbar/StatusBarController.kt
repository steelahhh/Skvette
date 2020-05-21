/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.core.statusbar

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Suppress("ObjectPropertyName")
object StatusBarController {
    private val _configuration: MutableStateFlow<Configuration> = MutableStateFlow(Configuration())
    private val configuration: Configuration get() = _configuration.value

    init {
        GlobalScope.launch {
            WindowInsetsHolder.flow()
                .map { insets -> configuration.copy(height = insets.top) }
                .collect { _configuration.value = it }
        }
    }

    val height get() = configuration.height

    fun flow(): Flow<Configuration> = _configuration

    // fun newColor(colorRef: ColorRef) = configurationRelay.accept(
    //     configuration?.copy(colorRef = colorRef) ?: Configuration(colorRef = colorRef)
    // )

    fun newVisibility(visible: Boolean) {
        _configuration.value = configuration.copy(visible = visible)
    }

    data class Configuration(
        val height: Int = 0,
        val visible: Boolean = false
        // val colorRef: ColorRef = ColorRef.Attribute(...)
    )
}
