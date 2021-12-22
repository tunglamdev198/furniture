package com.lamnt.furniture.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "production")
data class ProductionEntity(
    @PrimaryKey
    @NotNull
    val id: Int,
    @ColumnInfo(name = "shop_id") val shopId: Int,
    val name: String,
    val price: Float,
    var amount: Int,
    val image: String

)