package com.fulora.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by danielvilha on 17/04/21
 * https://github.com/danielvilha
 */
@Entity(tableName = "fulora_table")
data class Fulora(
    @PrimaryKey(autoGenerate = true)
    var fuloraId: Long = 0L
)