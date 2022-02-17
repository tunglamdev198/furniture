package com.lamnt.furniture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lamnt.furniture.data.database.dao.ProductionDao
import com.lamnt.furniture.data.database.entities.ProductionEntity

@Database(entities = [ProductionEntity::class], version = 1)
abstract class FurnitureDatabase : RoomDatabase() {
    abstract fun getProductionDao(): ProductionDao
}