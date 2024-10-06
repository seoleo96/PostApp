package com.seoleo.postapp.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseManagerService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // todo save and update new token for fcm
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d(TAG, "onMessageReceived: message.notification?.body - ${message.notification?.body}")
        Log.d(TAG, "onMessageReceived: message.notification?.title - ${message.notification?.title}")
        Log.d(TAG, "onMessageReceived: message.data - ${message.data}")
    }


    companion object {
        private const val TAG = "FirebaseManagerService"
    }
}