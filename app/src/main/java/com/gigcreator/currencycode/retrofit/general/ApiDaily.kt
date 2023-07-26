package com.gigcreator.currencycode.retrofit.general

import com.gigcreator.currencycode.retrofit.api.Daily
import retrofit2.http.GET

interface ApiDaily {
    @GET("daily_json.js")
    suspend fun getDaily(): Daily
}