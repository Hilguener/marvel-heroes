package com.hilguener.marvelsuperheroes.presentation.sign_up

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.hilguener.marvelsuperheroes.presentation.sign_in.LoadingButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SignUpScreen(navController: NavController, modifier: Modifier = Modifier) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .background(Color.Red)
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .padding(start = 16.dp, top = 100.dp, bottom = 40.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "Welcome to Marvel App.", fontWeight = FontWeight.Bold, color = Color.White
            )
        }
        Box(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            ConstraintLayout(
                modifier = modifier
                    .padding(horizontal = 24.dp, vertical = 40.dp)
                    .fillMaxSize()
            ) {
                val (nameField, emailField, passwordField, confirmPasswordField, signUpButton, loginText, marvelText) = createRefs()

                OutlinedTextField(value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text("Name") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null)
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .constrainAs(nameField) {
                            top.linkTo(parent.top)
                        })
                Spacer(modifier = modifier
                    .height(16.dp)
                    .constrainAs(createRef()) {
                        top.linkTo(nameField.bottom)
                    })
                OutlinedTextField(value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null)
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .constrainAs(emailField) {
                            top.linkTo(nameField.bottom, margin = 16.dp)
                        })
                Spacer(modifier = modifier
                    .height(16.dp)
                    .constrainAs(createRef()) {
                        top.linkTo(emailField.bottom)
                    })
                OutlinedTextField(value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Password") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray
                    ),
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    modifier = modifier
                        .fillMaxWidth()
                        .constrainAs(passwordField) {
                            top.linkTo(emailField.bottom, margin = 16.dp)
                        })
                Spacer(modifier = modifier
                    .height(16.dp)
                    .constrainAs(createRef()) {
                        top.linkTo(passwordField.bottom)
                    })
                OutlinedTextField(value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text("Confirm Password") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray
                    ),
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    modifier = modifier
                        .fillMaxWidth()
                        .constrainAs(confirmPasswordField) {
                            top.linkTo(passwordField.bottom, margin = 16.dp)
                        })
                Spacer(modifier = modifier
                    .height(32.dp)
                    .constrainAs(createRef()) {
                        top.linkTo(confirmPasswordField.bottom)
                    })
                LoadingButton(onClick = {
                    coroutineScope.launch {
                        isLoading.value = true
                        delay(2000)
                        isLoading.value = false
                    }
                },
                    text = "Sign Up",
                    isLoading = isLoading.value,
                    modifier = modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .constrainAs(signUpButton) {
                            top.linkTo(confirmPasswordField.bottom, margin = 32.dp)
                        })
                Text(text = "Already have an account? Sign in",
                    modifier = modifier
                        .constrainAs(loginText) {
                            top.linkTo(signUpButton.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .clickable {
                            navController.navigate("sign_in_screen") {
                                popUpTo("sign_up_screen") {
                                    inclusive = true
                                }
                            }
                        })
                Text(text = "© Marvel 2024",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = modifier.constrainAs(marvelText) {
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            }
        }
    }
}


@Preview(showBackground = false)
@Composable
fun SignInScreenPreview() {
    val context = LocalContext.current
    val navController = NavController(context)
    SignUpScreen(navController)
}
