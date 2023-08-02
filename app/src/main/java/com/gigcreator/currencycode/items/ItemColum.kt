package com.gigcreator.currencycode.items

import androidx.compose.foundation.Image
import com.gigcreator.currencycode.retrofit.api.Coin
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gigcreator.currencycode.ui.theme.CardColor

@Composable
fun ItemColum(item: Coin, up: Int, down: Int) {
    //if (item.previous > item.value) Color.Red else Color.Green

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .background(CardColor)
            .clickable { isExpanded = !isExpanded }
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = item.charCode, color = Color.White)
            Text(text = item.name, color = Color.White)

            if (isExpanded) {
                Text(
                    text ="Nominal: ${item.nominal}",
                    color = Color.Yellow
                )
                Row() {
                    Text(
                        text = "Value: ${item.value}",
                        color = if (item.previous > item.value) Color.Red else Color.Green
                    )
                    Image(
                        painter = painterResource(id = if (item.previous > item.value) down else up),
                        contentDescription = item.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(20.dp).clip(CircleShape)
                    )
                }
            }
        }
    }
}