package com.example.mycinemas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycinemas.R

@Composable
fun HomeUser(
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
){
        Row(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = modifier) {
                Text(
                    text = "Selamat datang User",
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    fontSize = 11.sp
                )
                Text(
                    text = "Informasi seputar film bioskop",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    fontSize = 16.sp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "about_page",
                modifier = modifier.size(30.dp).clickable {
                    navigateToProfile()
                },
            )

        }
}