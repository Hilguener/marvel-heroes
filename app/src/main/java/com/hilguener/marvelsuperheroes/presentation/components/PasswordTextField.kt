package com.hilguener.marvelsuperheroes.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    focusedBorderColor: Color = Color.Red,
    unfocusedBorderColor: Color = Color.Gray,
    leadingIcon: @Composable (() -> Unit) = { Icon(Icons.Default.Lock, contentDescription = null) },
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(label) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            PasswordVisibilityIcon(
                showPassword = passwordVisible,
                onTogglePasswordVisibility = onPasswordVisibilityChange,
            )
        },
        colors =
            OutlinedTextFieldDefaults.colors(
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
            ),
        leadingIcon = leadingIcon,
        modifier =
            modifier
                .fillMaxWidth(),
    )
}
