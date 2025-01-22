package com.diego.agendadecontatoscompose.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diego.agendadecontatoscompose.R
import com.diego.agendadecontatoscompose.ui.theme.Purple500
import com.diego.agendadecontatoscompose.ui.theme.WHITE

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListaContatos(navController: NavController){

    Scaffold(
        modifier = Modifier.statusBarsPadding(),// padding na aba de notificações
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Agenda de Contatos", fontSize = 18.sp, color = WHITE)
                },
                colors = TopAppBarDefaults.topAppBarColors(Purple500)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //navController.navigate("salvarContato")
                    navController.navigate("salvarContato")
                },
                shape = RoundedCornerShape(50.dp), // Define o raio do canto arredondado
                containerColor = Purple500,
                contentColor = WHITE
            ) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_add), contentDescription = "Icone de adicionar novo contato")
            }
        }
    ) {

    }
}


@Preview
@Composable
fun ListaContatoPreview(){
    ListaContatos(navController = rememberNavController())
}
