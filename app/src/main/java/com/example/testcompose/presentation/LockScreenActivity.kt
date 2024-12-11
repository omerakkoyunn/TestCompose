package com.example.testcompose.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.testcompose.R
import com.example.testcompose.service.OverlayService
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class LockScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Kilit ekranında görünmesini sağlamak için
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        }

        // Kilit ekranı için pencereyi tam ekran yapabilirsiniz
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)  // Kilit ekranını kaldırmak
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)   // Ekranı açık tutmak

        setContent {
           // ProductInfoOverlay()

        }
    }

}

@Composable
fun ProductInfoOverlay() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White),
        contentAlignment = Alignment.TopStart
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.vodafone_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(44.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Başlık
            Text(
                text = "Ürün Bilgileri",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Özellikler
            ProductFeature("Üretim Yeri", "Türkiye")
            ProductFeature("Marka", "Samsung")
            ProductFeature("Model", "Galaxy S24")
            ProductFeature("Renk", "Mavi")
            ProductFeature("Fiyat", "₺31,999.99", textStyle = TextStyle(textDecoration = TextDecoration.LineThrough))
            ProductFeature("İndirimli Fiyat", "₺29,999.99", textStyle = TextStyle(fontWeight = FontWeight.Bold, color = Color.Red))
            ProductFeature("Geçerlilik Tarihi", "01.12.2024 - 15.12.2024")

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProductFeature(
    title: String,
    value: String,
    textStyle: TextStyle = TextStyle.Default
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.merge(textStyle),
            modifier = Modifier.weight(1f),
            textAlign = androidx.compose.ui.text.style.TextAlign.End
        )
    }
}
