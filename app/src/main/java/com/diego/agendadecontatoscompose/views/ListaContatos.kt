package com.diego.agendadecontatoscompose.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ListaContatos(navController: NavController){

    Column(
        modifier = Modifier.statusBarsPadding() // Adiciona padding automaticamente respeitando a barra de status
    ) {
        Text(text = "Lista de Contatos")
    }
}
