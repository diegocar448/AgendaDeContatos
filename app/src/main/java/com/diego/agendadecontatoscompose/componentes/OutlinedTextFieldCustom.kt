package com.diego.agendadecontatoscompose.componentes

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.diego.agendadecontatoscompose.ui.theme.Purple500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier
){
    OutlinedTextField(
        value,
        onValueChange,
        label = label,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Purple500,
            focusedBorderColor = Purple500
        ),
        modifier = modifier,
        maxLines = 1
    )
}