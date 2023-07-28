package com.gigcreator.currencycode

import com.gigcreator.currencycode.retrofit.api.Coin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gigcreator.currencycode.items.ItemColum
import com.gigcreator.currencycode.retrofit.api.Daily
import com.gigcreator.currencycode.retrofit.general.ApiDaily
import com.gigcreator.currencycode.ui.theme.BackgroundColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {

            val result = try {
                callGetApi().getDaily()
            }
            catch (e: Throwable){
                Daily()
            }

            runOnUiThread {
                setContent {
                   MainContent(result = result)
                }
            }
        }
    }
    private fun callGetApi(): ApiDaily{
        return Retrofit.Builder().baseUrl("https://www.cbr-xml-daily.ru")
            .addConverterFactory(JacksonConverterFactory.create()).build()
            .create(ApiDaily::class.java)
    }
}
@Composable
private fun MainContent(result: Daily){
    val up = R.drawable.baseline_arrow_up
    val down = R.drawable.baseline_arrow_down
    //Menu
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth().background(BackgroundColor),
            Arrangement.SpaceEvenly
        ) {
            Text(text = "Код", color = Color.White)
            Text(text = "Номинал", color = Color.White)
            Text(text = "Цена", color = Color.White)
            Text(text = "Знак", color = Color.White)
        }
        //List
        val list = ArrayList<Coin>()
        result.stats.forEach { (_, u) ->
            list.add(u)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            itemsIndexed(list) { _, item ->
                ItemColum(item = item, up, down)
            }
        }
    }
}
