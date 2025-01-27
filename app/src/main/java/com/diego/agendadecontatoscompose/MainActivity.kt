package com.diego.agendadecontatoscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.diego.agendadecontatoscompose.ui.theme.AgendaDeContatosComposeTheme
import com.diego.agendadecontatoscompose.views.AtualizarContato
import com.diego.agendadecontatoscompose.views.ListaContatos
import com.diego.agendadecontatoscompose.views.SalvarContato

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgendaDeContatosComposeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "listaContatos"
                ){
                    composable("listaContatos"){
                        ListaContatos(navController)
                    }
                    composable("salvarContato"){
                        SalvarContato(navController)
                    }
                    composable(
                        "atualizarContato/{uid}",
                        arguments = listOf(navArgument("uid"){})
                    ){
                        AtualizarContato(navController, it.arguments?.getString("uid").toString())
                    }
                }

            }
        }
    }
}

