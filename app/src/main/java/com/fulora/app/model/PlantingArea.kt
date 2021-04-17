package com.fulora.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "planting_area",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PlantingArea(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val picture: String,
    @ColumnInfo(name = "user_id")
    val userId: Int
)
