package com.diego.agendadecontatoscompose.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diego.agendadecontatoscompose.componentes.Botao
import com.diego.agendadecontatoscompose.componentes.OutlinedTextFieldCustom
import com.diego.agendadecontatoscompose.ui.theme.Purple500
import com.diego.agendadecontatoscompose.ui.theme.WHITE


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarContato(navController: NavController){

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
                    if(
                        nome.isEmpty() || sobreNome.isEmpty() ||
                        idade.isEmpty() || celular.isEmpty()
                        ){
                        println("Preencha os campos men")
                    }else{
                        println("Ai sim!")
                    }
                    navController.navigate("atualizarContato")
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