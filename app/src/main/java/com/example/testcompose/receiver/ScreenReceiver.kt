package com.example.testcompose.receiver

import android.app.KeyguardManager
import android.app.Service.WINDOW_SERVICE
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.KEYGUARD_SERVICE
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.testcompose.MainActivity
import com.example.testcompose.presentation.LockScreenActivity
import com.example.testcompose.service.OverlayService

class ScreenReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_SCREEN_OFF) {
            Log.d("ScreenReceiver", "Ekran kilitlendi")
            // Kilit ekranı aktif oldu, Activity başlat
            val activityIntent = Intent(context, LockScreenActivity::class.java)
            activityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(activityIntent)
        } else if (intent.action == Intent.ACTION_SCREEN_ON) {
            Log.d("ScreenReceiver", "Ekran açıldı")
            // Ekran açıldı, Service'i başlat
            val serviceIntent = Intent(context, LockScreenActivity::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
            } else {
                context.startService(serviceIntent)
            }
        } else if (intent.action == Intent.ACTION_USER_PRESENT) {
            // Kilit ekranı açıldı
            Log.d("ScreenReceiver", "Kilit ekranı açıldı")

            val serviceIntent = Intent(context, LockScreenActivity::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
            } else {
                context.startService(serviceIntent)
            }

        }
    }
}