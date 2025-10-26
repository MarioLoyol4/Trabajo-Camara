package com.example.trabajo_camara.components

import android.net.Uri
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImagenInteligente(
    modifier: Modifier = Modifier,
    uri: Uri?
){
    if (uri != null ){
        AsyncImage(
            model = uri,
            contentDescription = "Imagen de perfil seleccionada",
            modifier = modifier
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    } else {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Icono de perfil por defecto",
            modifier = modifier.size(150.dp)
        )
    }
}