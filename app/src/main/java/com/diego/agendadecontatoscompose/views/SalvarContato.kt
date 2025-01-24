package com.diego.agendadecontatoscompose.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diego.agendadecontatoscompose.AppDatabase
import com.diego.agendadecontatoscompose.componentes.Botao
import com.diego.agendadecontatoscompose.componentes.OutlinedTextFieldCustom
import com.diego.agendadecontatoscompose.dao.ContatoDao
import com.diego.agendadecontatoscompose.model.Contato
import com.diego.agendadecontatoscompose.ui.theme.Purple500
import com.diego.agendadecontatoscompose.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private lateinit var contatoDao: ContatoDao

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarContato(navController: NavController){

    val listaContatos: MutableList<Contato> = mutableListOf()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var nome by remember {
        mutableStateOf("")
    }
    var sobreNome by remember {
        mutableStateOf("")
    }
    var idade by remember {
        mutableStateOf("")
    }
    var celular by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Salvar novo Contato ", fontSize = 18.sp, color = WHITE)
                },
                colors = TopAppBarDefaults.topAppBarColors(Purple500)
            )
        }
    ){ paddingValues -> // Recebe o padding fornecido pelo Scaffold
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues) // Aplica o padding aqui
                .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top


        ) {

            OutlinedTextFieldCustom(
                value = nome,
                onValueChange = {
                    nome = it
                },
                label = {
                    Text(text = "Nome")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier.fillMaxSize().padding(20.dp, 80.dp, 20.dp, 10.dp)
            )
            OutlinedTextFieldCustom(
                value = sobreNome,
                onValueChange = {
                    sobreNome = it
                },
                label = {
                    Text(text = "Sobrenome")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier.fillMaxSize().padding(20.dp, 0.dp, 20.dp, 10.dp)
            )
            OutlinedTextFieldCustom(
                value = idade,
                onValueChange = {
                    idade = it
                },
                label = {
                    Text(text = "Idade")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxSize().padding(20.dp, 0.dp, 20.dp, 10.dp)
            )
            OutlinedTextFieldCustom(
                value = celular,
                onValueChange = {
                    celular = it
                },
                label = {
                    Text(text = "Celular")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                modifier = Modifier.fillMaxSize().padding(20.dp, 0.dp, 20.dp, 10.dp)
            )

            Botao(
                onClick = {

                    var mensagem = false

                    //aqui rodamos em nossa thread paralela, poupa a thread principal
                    scope.launch(Dispatchers.IO){
                        if(
                            nome.isEmpty() || sobreNome.isEmpty() ||
                            idade.isEmpty() || celular.isEmpty()
                        ){
                            mensagem = false

                        }else{

                            mensagem = true
                            val contato = Contato(nome, sobreNome, idade, celular)
                            listaContatos.add(contato)

                            //inicia aqui o BD
                            val contatoDao = AppDatabase.getInstance(context).contatoDao()
                            contatoDao.gravar(listaContatos)
                            println("Chegou aqui")
                        }
                    }

                    //sendo algo visual usamos a thread principal
                    scope.launch(Dispatchers.Main){
                        if(mensagem){
                            Toast.makeText(context, "Sucesso ao salvar o contato!", Toast.LENGTH_SHORT).show()
                            //voltar tela anterior
                            navController.popBackStack()
                        }else{
                            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                        }
                    }

                },
                texto = "Salvar"
            )



        }
    }
}

@Preview
@Composable
private fun SalvarContatoPreview(){
    SalvarContato(navController = rememberNavController())
}