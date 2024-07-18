package com.hilguener.marvelsuperheroes.presentation.signup.screen

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.compose.rememberNavController
import com.example.compose.MarvelSuperHeroesTheme
import com.hilguener.marvelsuperheroes.presentation.components.EmailTextField
import com.hilguener.marvelsuperheroes.presentation.components.LoadingButton
import com.hilguener.marvelsuperheroes.presentation.components.PasswordTextField
import com.hilguener.marvelsuperheroes.presentation.signup.event.SignUpFormEvent
import com.hilguener.marvelsuperheroes.presentation.signup.vm.SignUpViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
internal fun SignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val viewModel: SignUpViewModel = koinViewModel()
    val state = viewModel.state
    val context = LocalContext.current
    val passwordVisible = remember { mutableStateOf(false) }
    val confirmPasswordVisible = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(context) {
        viewModel.validationEvent.collect { event ->
            when (event) {
                is SignUpViewModel.ValidationEvent.Success -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    navController.navigate("main_screen"){
                        popUpTo("sign_up_screen") {
                            inclusive = true
                        }
                    }
                }
                is SignUpViewModel.ValidationEvent.Error -> {
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
                text = "Sign Up",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Welcome to Marvel App.",
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
                val (nameField, nameError, emailField, emailError, passwordField, passwordError, confirmPasswordField, confirmPasswordError, signUpButton, loginText, marvelText) = createRefs()

                OutlinedTextField(
                    value = state.name,
                    onValueChange = { viewModel.onEvent(SignUpFormEvent.NameChanged(it)) },
                    isError = state.nameError != null,
                    label = { Text("Name") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Red,
                        unfocusedBorderColor = Color.Gray,
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null)
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(nameField) {
                            top.linkTo(parent.top)
                        },
                )
                if (state.nameError != null) {
                    Text(
                        text = state.nameError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.constrainAs(nameError) {
                            top.linkTo(nameField.bottom, margin = 4.dp)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                EmailTextField(
                    email = state.email,
                    onEmailChange = { viewModel.onEvent(SignUpFormEvent.EmailChanged(it)) },
                    modifier = Modifier.constrainAs(emailField) {
                        top.linkTo(
                            if (state.nameError != null) nameError.bottom else nameField.bottom,
                            margin = 16.dp
                        )
                    },
                    isError = viewModel.state.emailError != null,
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
                    onPasswordChange = { viewModel.onEvent(SignUpFormEvent.PasswordChanged(it)) },
                    passwordVisible = passwordVisible.value,
                    label = "Password",
                    onPasswordVisibilityChange = { passwordVisible.value = !passwordVisible.value },
                    modifier = Modifier.constrainAs(passwordField) {
                        top.linkTo(
                            if (state.emailError != null) emailError.bottom else emailField.bottom,
                            margin = 16.dp
                        )
                    },
                    imeAction = ImeAction.Next,
                    isError = state.passwordError != null,
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
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(
                    password = state.confirmPassword,
                    onPasswordChange = { viewModel.onEvent(SignUpFormEvent.ConfirmPasswordChanged(it)) },
                    passwordVisible = confirmPasswordVisible.value,
                    label = "Confirm Password",
                    onPasswordVisibilityChange = {
                        confirmPasswordVisible.value = !confirmPasswordVisible.value
                    },
                    modifier = Modifier.constrainAs(confirmPasswordField) {
                        top.linkTo(
                            if (state.passwordError != null) passwordError.bottom else passwordField.bottom,
                            margin = 16.dp
                        )
                    },
                    imeAction = ImeAction.Done,
                    isError = state.confirmPasswordError != null
                )
                if (state.confirmPasswordError != null) {
                    Text(
                        text = state.confirmPasswordError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.constrainAs(confirmPasswordError) {
                            top.linkTo(confirmPasswordField.bottom, margin = 4.dp)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                LoadingButton(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.onEvent(SignUpFormEvent.Submit)
                        }
                    },
                    text = "Sign Up",
                    isLoading = viewModel.isLoading,
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .constrainAs(signUpButton) {
                            top.linkTo(
                                if (state.confirmPasswordError != null) confirmPasswordError.bottom else confirmPasswordField.bottom,
                                margin = 32.dp
                            )
                        },
                )
                Text(
                    text = "Already have an account? Sign in",
                    modifier = Modifier
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
                        },
                )
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




@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    MarvelSuperHeroesTheme {
        val fakeNavController = rememberNavController()
        SignUpScreen(navController = fakeNavController)
    }

}

@Preview(showBackground = false)
@Composable
fun SignUpScreenDarkPreview() {
    MarvelSuperHeroesTheme(darkTheme = true) {
        val fakeNavController = rememberNavController()
        SignUpScreen(navController = fakeNavController)
    }
}
