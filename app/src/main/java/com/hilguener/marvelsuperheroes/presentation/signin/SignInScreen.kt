package com.hilguener.marvelsuperheroes.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.compose.MarvelSuperHeroesTheme
import com.hilguener.marvelsuperheroes.R
import com.hilguener.marvelsuperheroes.presentation.components.EmailTextField
import com.hilguener.marvelsuperheroes.presentation.components.LoadingButton
import com.hilguener.marvelsuperheroes.presentation.components.PasswordTextField
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    state: SignInState,
) {
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    val email = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier =
        modifier
            .background(Color.Red)
            .fillMaxSize(),
    ) {
        Column(
            modifier =
            modifier
                .padding(start = 16.dp, top = 100.dp, bottom = 40.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Sign In",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "Welcome to Marvel app.",
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
        Box(
            modifier =
            modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(MaterialTheme.colorScheme.background),
        ) {
            ConstraintLayout(
                modifier =
                modifier
                    .padding(horizontal = 24.dp, vertical = 40.dp)
                    .fillMaxSize(),
            ) {
                val (emailField, passwordField, forgotPasswordText, signInButton, googleButton, createAccountButton, marvelText) = createRefs()

                EmailTextField(
                    email = email.value,
                    onEmailChange = { email.value = it },
                    modifier =
                    modifier
                        .fillMaxWidth()
                        .constrainAs(emailField) {
                            top.linkTo(parent.top)
                        },
                )
                Spacer(
                    modifier =
                    modifier
                        .height(16.dp)
                        .constrainAs(createRef()) {
                            top.linkTo(emailField.bottom)
                        },
                )
                PasswordTextField(
                    password = password.value,
                    onPasswordChange = { password.value = it },
                    label = "Password",
                    passwordVisible = passwordVisible.value,
                    onPasswordVisibilityChange = { passwordVisible.value = !passwordVisible.value },
                    modifier =
                    Modifier.constrainAs(passwordField) {
                        top.linkTo(emailField.bottom, margin = 16.dp)
                    },
                    imeAction = ImeAction.Done
                )
                Spacer(
                    modifier =
                    modifier
                        .height(8.dp)
                        .constrainAs(createRef()) {
                            top.linkTo(passwordField.bottom)
                        },
                )
                Text(
                    text = "Forgot password?",
                    modifier =
                    modifier.constrainAs(forgotPasswordText) {
                        top.linkTo(passwordField.bottom, margin = 8.dp)
                        end.linkTo(parent.end)
                    },
                )
                Spacer(
                    modifier =
                    modifier
                        .height(32.dp)
                        .constrainAs(createRef()) {
                            top.linkTo(forgotPasswordText.bottom)
                        },
                )
                LoadingButton(
                    onClick = {
                        coroutineScope.launch {
                            isLoading.value = true
                            delay(2000)
                            isLoading.value = false
                        }
                    },
                    isLoading = isLoading.value,
                    text = "Sign In",
                    modifier =
                    modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .constrainAs(signInButton) {
                            top.linkTo(forgotPasswordText.bottom, margin = 32.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                )
                ElevatedCard(
                    onClick = { /* TODO: Implement Google Sign In */ },
                    modifier =
                    modifier
                        .fillMaxWidth()
                        .constrainAs(googleButton) {
                            bottom.linkTo(createAccountButton.top, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Row(
                        modifier = modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "",
                            modifier = modifier.size(25.dp),
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        Text(text = "Continue with Google", modifier = Modifier.weight(1f))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "")
                    }
                }
                ElevatedCard(
                    onClick = {
                        navController.navigate("sign_up_screen") {
                            popUpTo("sign_in_screen") {
                                inclusive = true
                            }
                        }
                    },
                    modifier =
                    modifier
                        .fillMaxWidth()
                        .constrainAs(createAccountButton) {
                            bottom.linkTo(marvelText.top, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Row(
                        modifier = modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "Create a new account", modifier = modifier.weight(1f))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "")
                    }
                }
                Text(
                    text = "© Marvel 2024",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier =
                    modifier.constrainAs(marvelText) {
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun SignInScreenPreview() {
    MarvelSuperHeroesTheme {
        val context = LocalContext.current
        val navController = NavController(context)
        val state = SignInState()
        SignInScreen(navController, state = state)
    }
}

@Preview
@Composable
fun SignInScreenDarkPreview() {
    MarvelSuperHeroesTheme(darkTheme = true) {
        val context = LocalContext.current
        val navController = NavController(context)
        val state = SignInState()
        SignInScreen(navController, state = state)
    }
}
