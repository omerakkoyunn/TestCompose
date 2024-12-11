package com.example.testcompose

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.testcompose.service.OverlayService


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            checkOverlayPermission()
        }
    }

    // Method to check overlay permissions
    private fun checkOverlayPermission() {
        if (!Settings.canDrawOverlays(this)) {
            // If overlay permission is not granted, request it
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                android.net.Uri.parse("package:$packageName"))
            startActivity(intent)
        } else {
            // If permission is granted, start overlay service
            startOverlayService()
        }
    }

    // Start the overlay service
    private fun startOverlayService() {
        val serviceIntent = Intent(this, OverlayService::class.java)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }

        finish()
    }



    override fun onDestroy() {
        super.onDestroy()
        // Stop the overlay service when the activity is destroyed
        val serviceIntent = Intent(this, OverlayService::class.java)
       // stopService(serviceIntent)
    }
}
