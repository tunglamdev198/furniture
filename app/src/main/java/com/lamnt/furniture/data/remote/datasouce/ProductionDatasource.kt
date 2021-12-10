package com.lamnt.furniture.data.remote.datasouce

import com.lamnt.furniture.model.dto.Production

class ProductionDatasource {
    fun getHomeProductions() = arrayListOf(
        Production(1, "LOBERGET", "", 29.99f, "https://www.ikea.com/us/en/images/products/loberget-blyskaer-swivel-chair-white__0806542_pe770243_s5.jpg?f=xl", 4.3f, 1, 1, 100, 1, "You sit comfortably since the chair is adjustable in height.\n" +
                "The safety casters have a pressure-sensitive brake mechanism that keeps the chair in place when you stand up, and releases automatically when you sit down.\n\n" +
                "Easy to keep clean by just wiping with a damp cloth.\n\n" +
                "To adjust the seat height, spin the seat upwards or downwards.\n\n" +
                "This product has been developed and tested for domestic use.\n\n" +
                "May be used with KOLON floor protector.\n\n" +
                "Possible to separate for recycling or energy recovery if available in your community.\n\n" +
                "Designer\n\n" +
                "Carl Öjerstam/IKEA of Sweden", 1639074605093, 1639074605093),
        Production(2, "BEKANT", "", 259.00f, "https://www.ikea.com/us/en/images/products/bekant-corner-desk-left-white__0853123_pe714709_s5.jpg?f=xl", 4.5f, 1, 2, 120, 1, "10-year Limited Warranty. Read about the terms in the Limited Warranty brochure.\n\n" +
                "The melamine surface is durable, stain resistant and easy to keep clean.\n\n" +
                "You can mount the table top at a height that suits you, since the legs are adjustable between 25⅝\"- 33½\".\n\n" +
                "It’s easy to keep your desk neat and tidy with the cable management net under the table top.\n\n" +
                "The contoured table top provides support to the wrists and forearms when writing.\n\n" +
                "Deep table top gives a generous work surface and lets you sit at a comfortable distance from the computer monitor.\n\n" +
                "This desk has been tested for office use and meets the requirements for durability and stability set forth in the following standards: EN 527-2 and ANSI/BIFMA X5.5.", 1639074605093, 1639074605093),
        Production(3, "ÖRFJÄLL", "", 39.99f, "https://www.ikea.com/us/en/images/products/oerfjaell-childs-desk-chair-white-vissle-light-gray__0977960_pe813958_s5.jpg?f=xl", 4.8f, 1, 1, 95, 1, "Desk chair with a nicely upholstered seat and backrest to lean against.\n" +
                "Simple for the child to change the sitting position by putting their feet on the chair legs, spinning lightly or rolling the castors of the chair.\n\n" +
                "Keep the chair clean by vacuuming it regularly and wiping it with a slightly damp cloth.\n\n" +
                "May be used with KOLON floor protector.\n\n" +
                "Recommended for ages 6-12 years.", 1639074605093, 1639074605093),
        Production(4, "ALEX", "", 89.00f, "https://www.ikea.com/us/en/images/products/alex-drawer-unit-white__0977775_pe813763_s5.jpg?f=s", 4.8f, 1, 3, 80, 1, "Drawer stops prevent the drawer from being pulled out too far.\n\n" +
                "This unit can be placed anywhere in the room because it is finished on the back.\n\n" +
                "This product has been developed and tested for domestic use.", 1639074605093, 1639074605093),
        Production(5, "LAGKAPTEN", "", 207.99f, "https://www.ikea.com/us/en/images/products/lagkapten-alex-desk-white__1022432_pe832720_s5.jpg?f=xl", 5.0f, 1, 2, 80, 1, "The table top is made of board-on-frame, a strong and lightweight material.\n\n" +
                "The cut-out handles not only add character to the drawer units, they also make it easy for you to grip and open the drawers.", 1639074605093, 1639074605093),

    )
}