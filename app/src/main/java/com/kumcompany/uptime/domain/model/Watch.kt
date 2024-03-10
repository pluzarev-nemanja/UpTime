package com.kumcompany.uptime.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kumcompany.uptime.util.Constants.WATCH_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = WATCH_DATABASE_TABLE)
data class Watch(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val image : String,
    val rating : Double,
    val brand: String,
    val model : String,
    val description: String,
    val lugToLug : Float,
    val thickness: Float,
    val caseDiameter :Float,
    val caseMaterial : String,
    val dialColor : String,
    val crystal : String,
    val waterResistance : String,
    val movement : String,
    val powerReserve : Int,
    val bracelet : String,
    val openCaseBack : String
)
