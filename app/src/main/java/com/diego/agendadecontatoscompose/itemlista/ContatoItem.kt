package com.diego.agendadecontatoscompose.itemlista

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.diego.agendadecontatoscompose.R
import com.diego.agendadecontatoscompose.ui.theme.ShapeCardView
import com.diego.agendadecontatoscompose.ui.theme.WHITE

@Composable
fun ContatoItem(){

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
                text = "Contato: Marcos Duarte Gomes",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.constrainAs(txtNome){
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(
                text = "Idade: 40",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.constrainAs(txtIdade){
                    top.linkTo(txtNome.bottom, margin = 3.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(
                text = "Número: 85198882488",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.constrainAs(txtCelular){
                    top.linkTo(txtIdade.bottom, margin = 3.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Button(
                onClick = {

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

@Preview
@Composable
private fun ContatoItemPreview(){
    ContatoItem()
}