package com.example.trabajo_camara.ui

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.example.trabajo_camara.components.ImagenInteligente
import com.example.trabajo_camara.viewmodel.PerfilViewModel

import java.io.File
import java.util.Objects

fun crearUriTemporal(context: Context): Uri{
    val file = File(context.cacheDir, "temp_image_${System.currentTimeMillis()}.jpg")
    return FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider",
        file
    )
}

@Composable
fun PerfilScreen(vm: PerfilViewModel){
    val uri by vm.uiState.collectAsState()
    val context = LocalContext.current
    var uriCamara: Uri? = null
    val launcherGaleria = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            vm.onFotoSeleccionada(uri)
        }
    )
    val launcherCamara = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success){
                vm.onFotoTomada(uriCamara)
            }
        }
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImagenInteligente(uri = uri)
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick =  {
            launcherGaleria.launch("image/*")
        }) {
            Text("Abrir Galeria")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            uriCamara = crearUriTemporal(context)
            launcherCamara.launch(uriCamara)
        }) {
            Text("Activar camara")
        }
    }
}