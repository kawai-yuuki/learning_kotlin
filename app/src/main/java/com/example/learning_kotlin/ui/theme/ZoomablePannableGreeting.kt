package com.example.app.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp  // sp単位のインポート
import kotlin.math.roundToInt

@Composable
fun ZoomablePannableGreeting(name: String, modifier: Modifier = Modifier) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(0.5f, 3f)  // ズーム倍率を制限
                    offset += pan  // パン（ドラッグ）による移動を加算
                }
            }
            .scale(scale)  // ズーム倍率をBox全体に適用
            .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) },  // パンによる移動を適用
        contentAlignment = Alignment.Center  // コンテンツを中央に配置
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Hi, my name is $name!",
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp  // 文字サイズを指定
            )
            Text(
                text = "足立さん！！",
                modifier = Modifier.padding(16.dp),
                fontSize = 32.sp  // 別の文字サイズを指定
            )
            Text(
                text = "START！",
                modifier = Modifier.padding(16.dp),
                fontSize = 24.sp  // さらに別の文字サイズを指定
            )
        }
    }
}
