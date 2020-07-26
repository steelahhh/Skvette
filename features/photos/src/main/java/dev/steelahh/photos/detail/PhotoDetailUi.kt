/*
 * Copyright (C) 2020. Alexander Efimenko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.steelahh.photos.detail

import android.view.ViewGroup
import androidx.compose.Composable
import androidx.compose.Recomposer
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.clickable
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Stack
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeightIn
import androidx.ui.layout.size
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.rounded.ArrowBack
import androidx.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.mdctheme.MaterialThemeFromMdcTheme
import dev.steelahhh.core.statusbar.StatusBarController

fun photoDetailUi(
    viewGroup: ViewGroup,
    actioner: (PhotoDetailAction) -> Unit,
    photoUrl: String
) = viewGroup.setContent(Recomposer.current()) {
    MaterialThemeFromMdcTheme {
        PlaceholderUi(photoUrl = photoUrl, actioner = actioner)
    }
}

@Composable
fun PlaceholderUi(photoUrl: String, actioner: (PhotoDetailAction) -> Unit) {
    Stack {
        Column(modifier = Modifier.fillMaxSize()) {
            CoilImage(
                data = photoUrl,
                modifier = Modifier.fillMaxWidth().preferredHeightIn(minHeight = 120.dp)
            )

            CircularProgressIndicator(
                modifier = Modifier.gravity(Alignment.CenterHorizontally).padding(16.dp)
            )
        }

        BackButton {
            actioner(PhotoDetailAction.GoBack)
        }
    }
}

@Composable
private fun BackButton(
    modifier: Modifier = Modifier
        .padding(
            start = 8.dp,
            top = StatusBarController.heightDp.dp + 8.dp,
            end = 8.dp
        )
        .size(48.dp),
    action: () -> Unit
) {
    Box(
        shape = CircleShape,
        backgroundColor = Color.White.copy(alpha = 0.7f),
        modifier = modifier + Modifier.clickable(onClick = action)
    ) {
        Icon(
            asset = Icons.Rounded.ArrowBack.copy(defaultHeight = 32.dp, defaultWidth = 32.dp),
            modifier = Modifier.padding(8.dp)
        )
    }
}
