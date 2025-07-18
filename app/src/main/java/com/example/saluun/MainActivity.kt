package com.example.saluun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.saluun.ui.theme.SaluunTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SaluunTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AuthNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}