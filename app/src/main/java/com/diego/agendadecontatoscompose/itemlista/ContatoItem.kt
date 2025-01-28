package com.diego.agendadecontatoscompose.itemlista

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.diego.agendadecontatoscompose.AppDatabase
import com.diego.agendadecontatoscompose.R
import com.diego.agendadecontatoscompose.dao.ContatoDao
import com.diego.agendadecontatoscompose.model.Contato
import com.diego.agendadecontatoscompose.ui.theme.ShapeCardView
import com.diego.agendadecontatoscompose.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatoDao: ContatoDao

@Composable
fun ContatoItem(
    navController: NavController,
    position: Int,
    listaContatos: MutableList<Contato>,
    context: Context
){

    var scope  = rememberCoroutineScope()

    val nome = listaContatos[position].nome
    val sobrenome = listaContatos[position].sobrenome
    val idade = listaContatos[position].idade
    val celular = listaContatos[position].celular
    val uid = listaContatos[position].uid

    val contato = listaContatos[position]


    fun alertDialogDeletarContato(){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deseja Excluir?")
            .setMessage("Tem certeza?")
        alertDialog.setPositiveButton("OK"){_,_ ->
            scope.launch(Dispatchers.IO){
                contatoDao = AppDatabase.getInstance(context).contatoDao()
                contatoDao.deletar(uid)
                //remover contato da lista no front
                listaContatos.remove(contato)
            }
            scope.launch(Dispatchers.Main){
                navController.navigate("listaContatos")
                Toast.makeText(context, "Contato removido com sucesso!", Toast.LENGTH_SHORT).show()
            }

        }

        alertDialog.setNeutralButton("Cancelar"){_,_ ->

        }
        alertDialog.show()
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = WHITE,
            contentColor = WHITE
        ),
        shape = ShapeCardView.medium,
        modifier = Modifier.fillMaxWidth().padding(10.dp, 20.dp, 10.dp, 10.dp)
    ){
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)

        ) {
            val (txtNome, txtSobreNome, txtIdade, txtCelular, btAtualizar, btDeletar) = createRefs()

            Text(
                text = "Contato: ${nome} ${sobrenome}",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.constrainAs(txtNome){
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(
                text = "Idade: ${idade}",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.constrainAs(txtIdade){
                    top.linkTo(txtNome.bottom, margin = 3.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(
                text = "Número: $celular",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.constrainAs(txtCelular){
                    top.linkTo(txtIdade.bottom, margin = 3.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Button(
                onClick = {
                    navController.navigate("atualizarContato/$uid")
                },
                modifier = Modifier.constrainAs(btAtualizar){
                    start.linkTo(txtCelular.end, margin = 20.dp)
                    top.linkTo(parent.top, margin = 50.dp)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = WHITE
                ),
                shape = RoundedCornerShape(50.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    disabledElevation = 0.dp,
                    defaultElevation = 0.dp
                )
            ){
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit),
                    contentDescription = "Icone de editar contato"
                )
            }


            Button(
                onClick = {
                    alertDialogDeletarContato()
                },
                modifier = Modifier.constrainAs(btDeletar){
                    start.linkTo(btAtualizar.end, margin = 0.dp)
                    top.linkTo(parent.top, margin = 50.dp)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = WHITE
                ),
                shape = RoundedCornerShape(50.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    disabledElevation = 0.dp,
                    defaultElevation = 0.dp
                )
            ){
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = "Icone de deletar contato"
                )
            }
        }
    }
}
