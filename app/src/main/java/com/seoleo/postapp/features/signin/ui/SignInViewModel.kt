package com.seoleo.postapp.features.signin.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.seoleo.postapp.features.signin.data.cloud.CurrentUserCloudDataSource
import com.seoleo.postapp.features.signin.data.repository.CurrentUserRepositoryImpl
import com.seoleo.postapp.features.signin.domain.usecase.GetCurrentUserUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignInViewModel : ViewModel() {

    fun getCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentUserUseCase = GetCurrentUserUseCaseImpl(CurrentUserRepositoryImpl(CurrentUserCloudDataSource()))
            currentUserUseCase.getUser().let {
                Log.d(TAG, "getCurrentUser: $it")
            }
        }
    }

    //todo() refactor ny clean arch
    fun createUser(email: String, password: String, name : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            if (authResult.user != null) {
                val profileUpdates = userProfileChangeRequest {
                    displayName = name
                }
                val updateUserName = authResult.user?.updateProfile(profileUpdates)?.await()
                Log.d(TAG, "createUser: updateUserName ${updateUserName.toString()}")
            }
            Log.d(TAG, "createUser: authResult ${authResult.user}")
            Log.d(TAG, "createUser: $authResult")
        }
    }

    //todo() refactor ny clean arch
    fun signIn(email: String, password: String, name : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
            if (authResult.user != null) {
                Log.d(TAG, "createUser: signIn ${authResult.user.toString()}")
            }
            Log.d(TAG, "createUser: authResult.user ${authResult.user}")
            Log.d(TAG, "createUser: $authResult")
        }
    }

    companion object {
        private const val TAG = "SignInViewModel"
    }
}