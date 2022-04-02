package com.ifgoiano.mvipattern.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ifgoiano.mvipattern.util.Constants.Companion.FIELD

data class Data(
    @Expose
    @SerializedName(FIELD)
    val name: String? = null
)
