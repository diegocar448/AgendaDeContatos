package com.diego.agendadecontatoscompose.itemlista

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.diego.agendadecontatoscompose.ui.theme.ShapeCardView
import com.diego.agendadecontatoscompose.ui.theme.WHITE

@Composable
fun ContatoItem(){

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        shape = ShapeCardView.medium,
        modifier = Modifier.fillMaxWidth().padding(10.dp, 20.dp, 10.dp, 10.dp)

    ){
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {  }
    }

}

@Preview
@Composable
private fun ContatoItemPreview(){
    ContatoItem()
}