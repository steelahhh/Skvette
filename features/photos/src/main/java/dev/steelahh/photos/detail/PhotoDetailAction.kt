/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail

import dev.steelahhh.data.models.PhotoUi

sealed class PhotoDetailAction {
    object GoBack : PhotoDetailAction()
    data class OpenUser(val userId: String) : PhotoDetailAction()
    data class OpenTag(val title: String) : PhotoDetailAction()
    data class Download(val photo: PhotoUi) : PhotoDetailAction()
    data class Like(val photo: PhotoUi) : PhotoDetailAction()
    data class AddToCollection(val photo: PhotoUi) : PhotoDetailAction()
    data class SetAsWallpaper(val photo: PhotoUi) : PhotoDetailAction()
    data class OpenInBrowser(val photo: PhotoUi) : PhotoDetailAction()
    object Refresh : PhotoDetailAction()
}
