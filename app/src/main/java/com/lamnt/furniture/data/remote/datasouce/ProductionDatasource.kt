package com.lamnt.furniture.data.remote.datasouce

import com.lamnt.furniture.model.dto.Production

class ProductionDatasource {
    fun getHomeProductions() = arrayListOf(
        Production(1, "Chair 1", "", 20f, "https://cdn.shopify.com/s/files/1/2660/5106/products/nezid69eg3xecfcezu46_1400x.jpg?v=1569295596", 4.3f, 1, 1, 100, 1, "This is Chair 1", 1639074605093, 1639074605093),
        Production(2, "Chair 2", "", 25f, "https://cdn.shopify.com/s/files/1/2660/5106/products/nezid69eg3xecfcezu46_1400x.jpg?v=1569295596", 4.5f, 1, 1, 120, 1, "This is Chair 2", 1639074605093, 1639074605093),
        Production(3, "Chair 3", "", 30f, "https://cdn.shopify.com/s/files/1/2660/5106/products/nezid69eg3xecfcezu46_1400x.jpg?v=1569295596", 4.8f, 1, 1, 95, 1, "This is Chair 3", 1639074605093, 1639074605093),
        Production(4, "Chair 4", "", 21f, "https://cdn.shopify.com/s/files/1/2660/5106/products/nezid69eg3xecfcezu46_1400x.jpg?v=1569295596", 4.0f, 1, 1, 80, 1, "This is Chair 4", 1639074605093, 1639074605093),

    )
}