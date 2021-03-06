/*
 * Copyright (C) 2019. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.data

import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

@Singleton
class ApiKeyInterceptor @Inject constructor() : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()

    val newHttpUrl = original.url.newBuilder()
      .addQueryParameter("client_id", BuildConfig.APP_ID)
      .build()

    val newRequest = original.newBuilder().url(newHttpUrl).build()

    return chain.proceed(newRequest)
  }
}
