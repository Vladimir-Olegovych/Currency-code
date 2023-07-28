package com.gigcreator.currencycode.items

import androidx.compose.foundation.Image
import com.gigcreator.currencycode.retrofit.api.Coin
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gigcreator.currencycode.ui.theme.CardColor

@Composable
fun ItemColum(item: Coin, up: Int, down: Int) {
    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .background(CardColor),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.81f),
            Arrangement.SpaceEvenly
        ) {
            Text(text = "${item.charCode}:", color = Color.White)
            Text(text = "${item.nominal}", color = Color.Yellow)
            Text(text = item.value.toString(), color = if (item.previous > item.value) Color.Red else Color.Green)
        }

        Image(
            painter = painterResource(id = if (item.previous > item.value) down else up),
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape))
    }
}