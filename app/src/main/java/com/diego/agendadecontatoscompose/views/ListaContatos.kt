package com.diego.agendadecontatoscompose.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ListaContatos(navController: NavController){

    Column() {
        Text(text = "Lista de Contatos")
    }
}
