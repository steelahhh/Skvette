/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data.models

import androidx.annotation.StringRes
import dev.steelahhh.data.R

sealed class InfoItem(
    @StringRes
    val key: Int,
    val value: String
) {

    data class VIEWS(val _value: String) : InfoItem(R.string.info_views_title, _value)
    data class DOWNLOADS(val _value: String) : InfoItem(R.string.info_downloads_title, _value)
    data class LIKES(val _value: String) : InfoItem(R.string.info_likes_title, _value)
}
