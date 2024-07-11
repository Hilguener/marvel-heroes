package com.hilguener.marvelsuperheroes.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Email",
    focusedBorderColor: Color = Color.Red,
    unfocusedBorderColor: Color = Color.Gray,
    leadingIcon: @Composable (() -> Unit) = { Icon(Icons.Default.Email, contentDescription = null) },
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text(label) },
        colors =
            OutlinedTextFieldDefaults.colors(
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
            ),
        leadingIcon = leadingIcon,
        modifier = modifier.fillMaxWidth(),
    )
}
