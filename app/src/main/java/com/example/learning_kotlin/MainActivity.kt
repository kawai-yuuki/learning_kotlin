package com.example.learning_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.ZoomablePannableGreeting
import com.example.app.ui.theme.ZoomableGreeting
import com.example.learning_kotlin.ui.theme.Learning_kotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Learning_kotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Komoda",
                        modifier = Modifier.padding(innerPadding)
                    )
                    ZoomableGreeting(//zoomするために作った
                        name = "KOMODA"
                    )
                    ZoomablePannableGreeting(
                        name = "KOMODA"
                    )
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(
        // MaterialThemeの色設定を使う
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Text(
            text = "Hi, my name is $name!",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Learning_kotlinTheme {
        Greeting("KOMODA")
    }
}
