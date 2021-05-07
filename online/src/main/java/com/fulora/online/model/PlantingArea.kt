package com.fulora.online.model

import com.google.firebase.database.IgnoreExtraProperties

/**
 * Created by danielvilha on 28/04/21
 * https://github.com/danielvilha
 */
@IgnoreExtraProperties
data class PlantingArea(
    var name: String? = "",
    var picture: String? = "",
    var userId: String? = "",
    var dateTime: String? = ""
)
