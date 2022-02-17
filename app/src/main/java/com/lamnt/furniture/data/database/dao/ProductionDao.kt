package com.lamnt.furniture.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lamnt.furniture.data.database.entities.ProductionEntity

@Dao
interface ProductionDao {

    @Query("SELECT * FROM production")
    fun getAllProduction(): List<ProductionEntity>

    @Query("SELECT * FROM production WHERE id=:id")
    fun getProduction(id: Int): ProductionEntity

    @Query("SELECT amount FROM production WHERE id=:id")
    fun getCurrentProductionAmount(id: Int): Int

    @Query("SELECT 1 FROM production WHERE id =:id")
    fun exits(id: Int): Boolean

    @Insert
    fun insertProduction(production: ProductionEntity)

    @Query("UPDATE production SET amount =:amount WHERE id=:id")
    fun updateAmount(id: Int, amount: Int)

    @Query("DELETE FROM production WHERE id=:id")
    fun removeProduction(id: Int)
}