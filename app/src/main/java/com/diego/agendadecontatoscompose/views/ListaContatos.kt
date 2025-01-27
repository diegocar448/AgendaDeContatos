package com.diego.agendadecontatoscompose.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diego.agendadecontatoscompose.AppDatabase
import com.diego.agendadecontatoscompose.R
import com.diego.agendadecontatoscompose.dao.ContatoDao
import com.diego.agendadecontatoscompose.itemlista.ContatoItem
import com.diego.agendadecontatoscompose.model.Contato
import com.diego.agendadecontatoscompose.ui.theme.Purple500
import com.diego.agendadecontatoscompose.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private lateinit var contatoDao: ContatoDao

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun ListaContatos(navController: NavController) {

    val context = LocalContext.current
    val listaContatos: MutableList<Contato> = mutableListOf()
    val scope = rememberCoroutineScope()

    // Thread to retrieve data from the database
    scope.launch(Dispatchers.IO) {
        contatoDao = AppDatabase.getInstance(context).contatoDao()
        val contatos = contatoDao.getContatos()

        for (contato in contatos) {
            listaContatos.add(contato)
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(), // Padding for notification bar
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
                    navController.navigate("salvarContato")
                },
                shape = RoundedCornerShape(50.dp), // Rounded corner radius
                containerColor = Purple500,
                contentColor = WHITE
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "Icone de adicionar novo contato"
                )
            }
        }
    ) {
        // Content below the TopAppBar
        Column(modifier = Modifier.fillMaxSize().padding(vertical = 100.dp)) {
            LazyColumn {
                itemsIndexed(listaContatos) { position, item ->
                    ContatoItem(navController, position, listaContatos, context)
                }
            }
        }
    }
}


@Preview
@Composable
fun ListaContatoPreview() {
    ListaContatos(navController = rememberNavController())
}
