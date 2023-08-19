package ru.vaa.vtask

import android.app.Application
import com.google.firebase.FirebaseApp

class VTaskApp: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}