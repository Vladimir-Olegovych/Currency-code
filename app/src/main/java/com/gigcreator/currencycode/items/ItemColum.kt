package com.gigcreator.currencycode.items

import com.gigcreator.currencycode.retrofit.api.Coin
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ItemColum(item: Coin) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Text(text = item.name + ": ", modifier = Modifier.fillMaxWidth(0.3f))
        Text(text = item.value.toString(), color = Color.Green)
    }
}