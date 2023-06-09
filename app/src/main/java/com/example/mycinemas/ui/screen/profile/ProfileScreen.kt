package com.example.mycinemas.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycinemas.di.Injection
import com.example.mycinemas.model.Biodata
import com.example.mycinemas.ui.ViewModelFactory

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit
){
    val biodata = viewModel.biodata.collectAsState().value

    DetailBiodata(biodata = biodata, onBackClick = navigateBack)

}

@Composable
fun DetailBiodata(
    biodata: Biodata,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
){
    Column {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.Black,
            modifier = modifier
                .size(40.dp)
                .padding(top = 16.dp, start = 16.dp)
                .clickable {
                    onBackClick()
                }
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = biodata.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(width = 200.dp, height = 220.dp)
                    .clip(CircleShape)
            )
            Text(
                text = biodata.nama,
                fontSize = 24.sp,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = biodata.email,
                style = MaterialTheme.typography.subtitle2.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}