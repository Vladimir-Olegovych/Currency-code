package com.gigcreator.currencycode.retrofit.api

import com.fasterxml.jackson.annotation.JsonProperty

class Coin {
    @JsonProperty("ID")
    val id = ""
    @JsonProperty("NumCode")
    val numCode = ""
    @JsonProperty("CharCode")
    val charCode = ""
    @JsonProperty("Nominal")
    val nominal = 1
    @JsonProperty("Name")
    val name = ""
    @JsonProperty("Value")
    val value = 0.0
    @JsonProperty("Previous")
    val previous = 0.0
}