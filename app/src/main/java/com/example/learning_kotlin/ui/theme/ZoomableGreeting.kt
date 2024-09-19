//zoomするために作ったコード
package com.example.app.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun ZoomableGreeting(name: String, modifier: Modifier = Modifier) {
    var scale by remember { mutableStateOf(1f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)  // 全体の背景色
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    scale = (scale * zoom).coerceIn(0.5f, 3f)  // ズーム倍率を制限
                }
            }
            .scale(scale)  // ズーム倍率をBox全体に適用
    ) {
        Surface(
            color = Color.Transparent,  // 背景を透明に設定
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Hi, my name is $name!",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
