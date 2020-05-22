/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.di

import dagger.Component
import dev.steelahh.photos.PhotosViewModel
import javax.inject.Singleton

@Component(modules = [PhotosModule::class])
@Singleton
interface PhotosComponent {
    val photosViewModelFactory: PhotosViewModel.Factory
}
