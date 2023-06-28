package com.techconnect.client

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class app:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}