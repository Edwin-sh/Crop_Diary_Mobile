package com.myapps.cropdiarymobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.myapps.cropdiarymobile.core.rememberWindowSize
import com.myapps.cropdiarymobile.ui.navigation.AppNavigation
import com.myapps.cropdiarymobile.ui.screens.SplashViewScreen
import com.myapps.cropdiarymobile.ui.theme.CropDiaryMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CropDiaryMobileTheme {
                val window = rememberWindowSize()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(windowSize = window)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, device = Devices.DESKTOP, showSystemUi = true)
@Composable
fun GreetingPreview() {
    CropDiaryMobileTheme {
        val window = rememberWindowSize()
        AppNavigation(windowSize = window)
    }
}