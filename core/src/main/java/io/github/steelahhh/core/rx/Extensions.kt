/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.steelahhh.core.rx

import io.reactivex.Single

fun <T, R> Single<List<T>>.mapList(
    mapper: (T) -> R
): Single<List<R>> = map { it.map(mapper) }
