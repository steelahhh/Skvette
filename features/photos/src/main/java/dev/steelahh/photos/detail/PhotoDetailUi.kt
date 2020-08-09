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
import androidx.compose.collectAsState
import androidx.compose.getValue
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.ScrollableColumn
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.ExperimentalLayout
import androidx.ui.layout.Stack
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.unit.dp
import dev.chrisbanes.accompanist.mdctheme.MaterialThemeFromMdcTheme
import dev.steelahh.photos.detail.components.ActionsRow
import dev.steelahh.photos.detail.components.CommonPhotoInfoRow
import dev.steelahh.photos.detail.components.PhotoHeader
import dev.steelahh.photos.detail.components.PhotoInfoRow
import dev.steelahh.photos.detail.components.ProfileRow
import dev.steelahh.photos.detail.components.TagsRow
import dev.steelahh.photos.detail.components.asColor
import dev.steelahhh.core.ColorRef
import dev.steelahhh.coreui.compose.ErrorComponent
import dev.steelahhh.coreui.compose.Loader
import dev.steelahhh.data.models.PhotoUi
import kotlinx.coroutines.flow.Flow

internal val CORNER_SIZE = 56.dp
internal val CONTENT_SHAPE = RoundedCornerShape(topRight = CORNER_SIZE)
internal val VISIBLE_IMAGE_HEIGHT = 420.dp
internal val ACTUAL_IMAGE_HEIGHT = VISIBLE_IMAGE_HEIGHT + CORNER_SIZE

@ExperimentalLayout
fun photoDetailUi(
    viewGroup: ViewGroup,
    stateFlow: Flow<PhotoDetailState>,
    actioner: (PhotoDetailAction) -> Unit,
    photoUrl: String,
    photoColor: ColorRef
) = viewGroup.setContent(Recomposer.current()) {
    MaterialThemeFromMdcTheme(useTextColors = true) {
        val state by stateFlow.collectAsState(PhotoDetailState(photoUrl))
        ScrollableColumn {
            Stack {
                PhotoHeader(
                    placeholder = photoUrl,
                    photo = state.photo?.url,
                    color = (state.photo?.colorRef ?: photoColor).asColor(),
                    actioner = actioner,
                )
                when {
                    state.isLoading -> LoadingContent(
                        modifier = Modifier
                            .gravity(Alignment.Center)
                            .padding(top = VISIBLE_IMAGE_HEIGHT)
                            .fillMaxWidth()
                    )
                    state.photo != null -> PhotoContent(
                        photo = state.photo!!,
                        modifier = Modifier.padding(top = VISIBLE_IMAGE_HEIGHT),
                        actioner = actioner
                    )
                    state.error != null -> ErrorComponent(
                        message = state.error!!,
                        onAction = { actioner(PhotoDetailAction.Refresh) }
                    )
                    else -> Text(text = "Well hello there")
                }
            }

        }

    }
}

@Composable
private fun LoadingContent(modifier: Modifier) {
    Surface(
        color = MaterialTheme.colors.surface,
        shape = CONTENT_SHAPE,
        modifier = modifier,
    ) {
        Loader(modifier = Modifier.padding(56.dp))
    }
}

@ExperimentalLayout
@Composable
fun PhotoContent(
    photo: PhotoUi,
    modifier: Modifier,
    actioner: (PhotoDetailAction) -> Unit
) {
    Surface(
        color = MaterialTheme.colors.surface,
        shape = CONTENT_SHAPE,
        modifier = modifier,
    ) {
        Column {
            ProfileRow(actioner, photo)
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            CommonPhotoInfoRow(photo)
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            photo.exif?.let { PhotoInfoRow(it) }
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            ActionsRow(photo, actioner)
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            TagsRow(photo, actioner)
        }
    }
}
