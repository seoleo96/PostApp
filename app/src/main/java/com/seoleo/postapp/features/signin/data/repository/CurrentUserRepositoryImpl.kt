package com.seoleo.postapp.features.signin.data.repository

import android.util.Log
import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.seoleo.postapp.features.signin.data.cloud.CurrentUserCloudDataSource
import com.seoleo.postapp.features.signin.domain.model.CurrentUserResult
import com.seoleo.postapp.features.signin.domain.repository.CurrentUserRepository

class CurrentUserRepositoryImpl(
    private val currentUserCloudDataSource: CurrentUserCloudDataSource
) : CurrentUserRepository {

    override fun getCurrentUser(): CurrentUserResult {
        return try {
            val firebaseUser : FirebaseUser? = currentUserCloudDataSource.getCurrentUser()
            Log.d(TAG, "getCurrentUser: $firebaseUser")
            return if(firebaseUser == null){
                CurrentUserResult.CurrentUserNotAuthorized
            }else{
                CurrentUserResult.CurrentUserData(
                    email = firebaseUser.email ?: "",
                    uuid = firebaseUser.uid,
                    phoneNumber = firebaseUser.phoneNumber ?: "",
                    photoUrl = firebaseUser.photoUrl ?: "".toUri(),
                    name = firebaseUser.displayName ?: "",
                )
            }
        }catch (e : Exception){
            Log.d(TAG, "getCurrentUser Catch: $e")
            CurrentUserResult.CurrentUserNotAuthorized
        }
    }

    companion object {
        private const val TAG = "CurrentUserRepositoryIm"
    }
}