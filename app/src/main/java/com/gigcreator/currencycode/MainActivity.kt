package com.gigcreator.currencycode

import com.gigcreator.currencycode.retrofit.api.Coin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gigcreator.currencycode.items.ItemColum
import com.gigcreator.currencycode.retrofit.general.ApiDaily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {

            val result = callGetApi().getDaily()
            runOnUiThread {
                setContent {
                    val list = ArrayList<Coin>()
                    result.stats.forEach { t, u ->
                        list.add(u)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                    ){
                        itemsIndexed(list){
                            _, item ->
                            ItemColum(item = item)
                        }
                    }
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
