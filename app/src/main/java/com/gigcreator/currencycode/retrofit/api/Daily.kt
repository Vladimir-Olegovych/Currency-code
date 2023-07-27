package com.gigcreator.currencycode.retrofit.api

import com.fasterxml.jackson.annotation.JsonProperty

class Daily {
    @JsonProperty("Date")
    val date = ""
    @JsonProperty("PreviousDate")
    val previousDate = ""
    @JsonProperty("PreviousURL")
    val previousURL = ""
    @JsonProperty("Timestamp")
    val timestamp = ""
    @JsonProperty("Valute")
    val stats = emptyMap<String, Coin>()
}