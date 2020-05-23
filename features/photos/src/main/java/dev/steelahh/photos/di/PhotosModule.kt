/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dev.steelahhh.data.di.DataModule

@AssistedModule
@Module(includes = [DataModule::class, AssistedInject_PhotosModule::class])
interface PhotosModule
