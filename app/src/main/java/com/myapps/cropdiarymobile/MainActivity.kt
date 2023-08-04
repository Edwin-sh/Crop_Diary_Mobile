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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.myapps.cropdiarymobile.core.rememberWindowSize
import com.myapps.cropdiarymobile.ui.navigation.AppNavigation
import com.myapps.cropdiarymobile.ui.theme.CropDiaryAppTheme
import com.myapps.cropdiarymobile.ui.theme.CropDiaryMobileTheme

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CropDiaryAppTheme {
                val window = rememberWindowSize()
                AppNavigation(windowSize = window)
            }
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true, device = Devices.PIXEL_2, showSystemUi = true)
@Composable
fun GreetingPreview() {
    CropDiaryMobileTheme {
        val window = rememberWindowSize()
        AppNavigation(windowSize = window)
    }
}