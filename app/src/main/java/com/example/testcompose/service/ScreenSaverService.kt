package com.example.testcompose.service

import android.service.dreams.DreamService

class ScreenSaverService : DreamService() {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        isInteractive = false // Kullanıcı etkileşimlerini devre dışı bırakır
        isFullscreen = true   // Tam ekran görünüm sağlar
        setContentView(com.example.testcompose.R.layout.activity_lock_screen) // Özelleştirilmiş görünümünüz
    }
}