package com.lamnt.furniture.model.dto

data class Production(
    val id: Int,
    val name: String,
    val slug: String,
    val price: Float,
    val image: String?,
    val rating: Float,
    val brandId: Int,
    val categoryId: Int,
    val quantity: Int,
    val status: Int,
    val description: String,
    val createdAt: Long,
    val updateAt: Long
){
    fun getDecimalsPart(): String {
        return ((price - price.toInt()) * 100.1f).toString().replace('.', '0')
    }
    fun getIntegerPart(): Int {
        return price.toInt()
    }
}
