package com.lamnt.furniture.data.remote.datasouce

import com.lamnt.furniture.model.dto.Production

class ProductionDatasource {
    fun getHomeProductions() = arrayListOf(
        Production(1, "Chair 1", "https://cdn.shopify.com/s/files/1/2660/5106/products/nezid69eg3xecfcezu46_1400x.jpg?v=1569295596", 4.3f, 200000f),
        Production(2, "Chair 2", "https://cdn.shopify.com/s/files/1/2660/5106/products/nezid69eg3xecfcezu46_1400x.jpg?v=1569295596", 3.5f, 200000f),
        Production(3, "Chair 3", "https://cdn.shopify.com/s/files/1/2660/5106/products/nezid69eg3xecfcezu46_1400x.jpg?v=1569295596", 4.8f, 200000f),
        Production(4, "Chair 4", "https://cdn.shopify.com/s/files/1/2660/5106/products/nezid69eg3xecfcezu46_1400x.jpg?v=1569295596", 2.5f, 200000f),
        Production(5, "Chair 5", "https://cdn.shopify.com/s/files/1/2660/5106/products/nezid69eg3xecfcezu46_1400x.jpg?v=1569295596", 4.0f, 200000f),
    )
}