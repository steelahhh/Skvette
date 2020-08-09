/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahhh.coreui.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dev.steelahhh.coreui.FragmentViewBindingDelegate
import dev.steelahhh.coreui.R

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

fun Fragment.withArguments(block: Bundle.() -> Unit): Fragment = apply {
    arguments = (arguments ?: Bundle()).apply(block)
}

fun Fragment.tryToOpenUrl(url: String) = requireContext().tryOpenUrlLink(url)

fun Fragment.tryToShare(link: String) = requireContext().tryToShare(link)

fun Context.tryToShare(link: String) = tryIgnoreCatch {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = ("text/plain")
    intent.putExtra(Intent.EXTRA_TEXT, link)
    startActivity(Intent.createChooser(intent, getString(R.string.action_share)))
}

fun Context.tryToOpenEmail(email: String) = tryIgnoreCatch {
    val testIntent = Intent(Intent.ACTION_VIEW)
    val data = Uri.parse("mailto:$email")
    testIntent.data = data
    startActivity(testIntent)
}

fun Fragment.openGalleryImagePicker(
    code: Int,
    title: String = "Select picture",
    isMultiple: Boolean = false,
    includeVideo: Boolean = false
) = tryIgnoreCatch {
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_GET_CONTENT
    if (includeVideo)
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*", "video/*"))
    if (isMultiple) {
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
    }
    startActivityForResult(Intent.createChooser(intent, title), code)
}

fun Context.tryOpenUrlLink(url: String) = tryIgnoreCatch {
    val browser = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    if (browser.resolveActivity(packageManager) != null)
        startActivity(browser)
    else
        Toast.makeText(this, R.string.common_url_app_not_found, Toast.LENGTH_SHORT).show()
}

private inline fun tryIgnoreCatch(action: () -> Unit) {
    try {
        action()
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}
