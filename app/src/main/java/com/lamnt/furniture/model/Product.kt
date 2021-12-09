package com.lamnt.furniture.model

data class Product (val id: Int, val name: String, val slug: String, val price: Double,
                    val image: String, val brandId: Int, val categoryId: Int, val quantity: Int,
                    val status: Int, val description: String, val createdAt: Long, val updateAt: Long)