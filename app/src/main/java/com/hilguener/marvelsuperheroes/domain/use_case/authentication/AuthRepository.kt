package com.hilguener.marvelsuperheroes.domain.use_case.authentication

import com.google.firebase.auth.AuthResult
import com.hilguener.marvelsuperheroes.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun signUp(email: String, password: String, name: String):  Flow<Resource<AuthResult>>
}