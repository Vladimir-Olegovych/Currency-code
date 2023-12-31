package com.gigcreator.currencycode

import com.gigcreator.currencycode.retrofit.api.Coin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        result.stats.forEach { (_, u) ->
            ItemColum(item = u, up = up, down = down)
        }
    }
}
