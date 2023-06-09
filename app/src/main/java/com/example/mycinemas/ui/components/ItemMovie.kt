package com.example.mycinemas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mycinemas.ui.theme.Shapes

@Composable
fun ItemMovie(
    image: String,
    title: String,
    modifier: Modifier = Modifier
){
    val imagePainter = rememberAsyncImagePainter(image)
    Column(modifier = modifier.width(190.dp)) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(width = 190.dp, height = 260.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.SemiBold
            ),
            fontSize = 14.sp
        )
    }
}