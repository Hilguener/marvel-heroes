package com.hilguener.marvelsuperheroes.presentation.signin.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.compose.MarvelSuperHeroesTheme
import com.hilguener.marvelsuperheroes.presentation.components.EmailTextField
import com.hilguener.marvelsuperheroes.presentation.components.LoadingButton
import com.hilguener.marvelsuperheroes.presentation.components.PasswordTextField
import com.hilguener.marvelsuperheroes.presentation.signin.event.SignInFormEvent
import com.hilguener.marvelsuperheroes.presentation.signin.vm.SignInViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun SignInScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val viewModel: SignInViewModel = koinViewModel()
    val state = viewModel.state
    val context = LocalContext.current
    val passwordVisible = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(context) {
        viewModel.validationEvent.collect { event ->
            when (event) {
                is SignInViewModel.ValidationEvent.Success -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    navController.navigate("main_screen") {
                        popUpTo("sign_in_screen") {
                            inclusive = true
                        }
                    }
                }

                is SignInViewModel.ValidationEvent.Error -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    Column(
        modifier = modifier
            .background(Color.Red)
            .fillMaxSize(),
    ) {
        Column(
            modifier = modifier
                .padding(start = 16.dp, top = 100.dp, bottom = 40.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Sign In",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Welcome to Marvel app.",
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
        Box(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(MaterialTheme.colorScheme.background),
        ) {
            ConstraintLayout(
                modifier = modifier
                    .padding(horizontal = 24.dp, vertical = 40.dp)
                    .fillMaxSize(),
            ) {
                val (emailField, emailError, passwordField, passwordError, forgotPasswordText, signInButton, googleButton, createAccountButton, marvelText) = createRefs()

                EmailTextField(
                    email = state.email,
                    onEmailChange = { viewModel.onEvent(SignInFormEvent.EmailChanged(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(emailField) {
                            top.linkTo(parent.top)
                        },
                    isError = state.emailError != null
                )
                if (state.emailError != null) {
                    Text(
                        text = state.emailError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.constrainAs(emailError) {
                            top.linkTo(emailField.bottom, margin = 4.dp)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(
                    password = state.password,
                    onPasswordChange = { viewModel.onEvent(SignInFormEvent.PasswordChanged(it)) },
                    label = "Password",
                    passwordVisible = passwordVisible.value,
                    onPasswordVisibilityChange = { passwordVisible.value = !passwordVisible.value },
                    modifier = Modifier.constrainAs(passwordField) {
                        top.linkTo(
                            if (state.emailError != null) emailError.bottom else emailField.bottom,
                            margin = 16.dp
                        )
                    },
                    imeAction = ImeAction.Done,
                    isError = state.passwordError != null
                )
                if (state.passwordError != null) {
                    Text(
                        text = state.passwordError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.constrainAs(passwordError) {
                            top.linkTo(passwordField.bottom, margin = 4.dp)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Forgot password?",
                    modifier = Modifier.constrainAs(forgotPasswordText) {
                        top.linkTo(passwordField.bottom, margin = 8.dp)
                        end.linkTo(parent.end)
                    },
                )
                Spacer(modifier = Modifier.height(32.dp))
                LoadingButton(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.onEvent(SignInFormEvent.Submit)
                        }
                    },
                    isLoading = viewModel.isLoading,
                    text = "Sign In",
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .constrainAs(signInButton) {
                            top.linkTo(forgotPasswordText.bottom, margin = 32.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                )
                ElevatedCard(
                    onClick = {
                        navController.navigate("sign_up_screen") {
                            popUpTo("sign_in_screen") {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(createAccountButton) {
                            bottom.linkTo(marvelText.top, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "Create a new account", modifier = Modifier.weight(1f))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "")
                    }
                }
                Text(
                    text = "© Marvel 2024",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.constrainAs(marvelText) {
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
        SignInScreen(navController)
    }
}

@Preview
@Composable
fun SignInScreenDarkPreview() {
    MarvelSuperHeroesTheme(darkTheme = true) {
        val context = LocalContext.current
        val navController = NavController(context)
        SignInScreen(navController)
    }
}
