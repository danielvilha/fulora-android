package com.fulora.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

/**
 * Created by danielvilha on 17/04/21
 * https://github.com/danielvilha
 */
@Entity(
    tableName = "plant",
    foreignKeys = [ForeignKey(
        entity = PlantingArea::class,
        parentColumns = ["id"],
        childColumns = ["planting_area_id"],
        onDelete = CASCADE
    )],
)
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "planting_area_id")
    val plantingAreaId: Int,
    val name: String,
    val picture: String,
    val frequency: Int,
    val time: String,
    val water: Int
)