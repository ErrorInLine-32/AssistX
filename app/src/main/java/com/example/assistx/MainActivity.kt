package com.example.assistx

import SplashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assistx.navigation.AssistXNavigation
import com.example.assistx.navigation.AssistX_Screens
import com.example.assistx.screens.Login.LoginScreen
import com.example.assistx.ui.theme.AssistXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssistXTheme {
                AssistXApp()
            }
        }
    }
}

@Composable
fun AssistXApp() {
    Surface(modifier = Modifier
        .fillMaxSize() , color = Color.White) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            AssistXNavigation()
        }
    }
}
