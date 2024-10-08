package com.seoleo.postapp.features.signin.data.cloud

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CurrentUserCloudDataSource {

    fun getCurrentUser() : FirebaseUser? {
        return Firebase.auth.currentUser
    }
}