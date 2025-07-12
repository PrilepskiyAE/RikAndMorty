package com.prilepskiy.prisentation.uiComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.prilepskiy.common.DEFAULT_INT
import com.prilepskiy.domain.model.UiCharacnedModel

@Composable
fun CharacnedComponent(item: UiCharacnedModel?, goToUser: (Int) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = {
                goToUser.invoke(item?.id ?: DEFAULT_INT)
            })
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .size(190.dp)
                .clip(RoundedCornerShape(10.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(item?.image)
                .crossfade(true)
                .build(),
            contentDescription = "AVATAR"
        )
        Column(
            Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(190.dp)
                .align(Alignment.BottomCenter)
                .background(Color.Black.copy(alpha = 0.6f))
        ) {
            Text(
                text = item?.name.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            Text(
                text = "${item?.gender} | ${item?.species}",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            Text(
                text = "${item?.status}",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )

        }
    }

}