package com.kumcompany.uptime.data.remote

import com.kumcompany.uptime.domain.model.ApiResponse
import com.kumcompany.uptime.domain.model.Watch
import java.io.IOException

class FakeWatchApi2 : WatchApi {

    private val watches: Map<Int, List<Watch>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4,
            5 to page5
        )
    }

    private var page1 = listOf(
        Watch(
            id = 1,
            image = "/images/daytona.jpg",
            rating = 5.0,
            brand = "Rolex",
            model = "Cosmograph Daytona",
            description = "This model features a white dial with snailed counters, 18 ct gold applique hour markers and hands with a Chromalight display, a highly-legible luminescent material.The central sweep seconds hand allows an accurate reading of 1/8 second, while the two counters on the dial display the lapsed time in hours and minutes. Drivers can accurately map out their track times and tactics without fail.",
            lugToLug = 46.6f,
            thickness = 11.9f,
            caseDiameter = 40f,
            caseMaterial = "Oystersteel",
            dialColor = "White",
            crystal = "Scratch-resistant sapphire",
            waterResistance = "100 m",
            movement = "4131",
            powerReserve = 72,
            bracelet = "Oysterflex Bracelet",
            openCaseBack = "No"
        ),
        Watch(
            id = 2,
            image = "/images/casio.jpg",
            rating = 3.8,
            brand = "Casio",
            model = "Vintage-A158WETG-9AEF",
            description = "A classic CASIO design, part of the Vintage Retro Collection. This timepiece features a digital display, an Illuminator, daily alarm, and stopwatch. Resistant to minor splashing.",
            lugToLug = 36.8f,
            thickness = 8.2f,
            caseDiameter = 36.8f,
            caseMaterial = "Stainless steel",
            dialColor = "Yellow gold",
            crystal = "Mineral crystal",
            waterResistance = "Splash resistant",
            movement = "Quartz",
            powerReserve = 7,
            bracelet = "Stainless steel",
            openCaseBack = "No"
        ),
        Watch(
            id = 3,
            image = "/images/explorer.jpg",
            rating = 3.2,
            brand = "Rolex",
            model = "EXPLORER II",
            description = "The Explorer II is equipped with an additional 24-hour display; a dedicated hand circles the dial in 24 hours rather than the usual 12, pointing to a fixed bezel with 24 hour graduations. This function enables the wearer to distinguish the hours of the day from the hours of the night, an essential feature for those who venture into environments where darkness reigns supreme – such as in the depths of caves, or where the sun never sets – like the polar regions in summer.",
            lugToLug = 50.1f,
            thickness = 12.5f,
            caseDiameter = 42f,
            caseMaterial = "Oystersteel",
            dialColor = "Black",
            crystal = "Scratch-resistant sapphire",
            waterResistance = "100 m",
            movement = "3285",
            powerReserve = 70,
            bracelet = "Oyster, three-piece solid links",
            openCaseBack = "No"
        )
    )
    private var page2 = listOf(
        Watch(
            id = 4,
            image = "/images/skx.jpg",
            rating = 2.8,
            brand = "Seiko",
            model = "SKX007K2",
            description = "The lineage of the 007 can be traced back to Seiko’s earliest divers, starting with the iconic 62MAS and moving through the 6105, 6306/9, and the 7002. First produced in 1996, the SKX007 is one of several modern divers from Seiko powered by the brand’s automatic 7s26 movement. Other variants feature the same case paired with different dial designs and colors, some of which were conceived for specific international markets. There are even variations based on where the watch was manufactured, and some collectors prize the “made in Japan” versions above all others.",
            lugToLug = 46f,
            thickness = 13.25f,
            caseDiameter = 42.5f,
            caseMaterial = "Steel",
            dialColor = "Black",
            crystal = "Hardlex crystal",
            waterResistance = "200 m",
            movement = "Seiko 7S26",
            powerReserve = 41,
            bracelet = "Steel",
            openCaseBack = "Yes"
        ),
        Watch(
            id = 5,
            image = "/images/speedmaster.jpg",
            rating = 4.9,
            brand = "Omega",
            model = "SPEEDMASTER MOONWATCH PROFESSIONAL",
            description = "The Speedmaster Moonwatch is one of the world’s most iconic timepieces. Having been a part of all six moon landings, the legendary chronograph is an impressive representation of the brand’s adventurous pioneering spirit.Presented on a brushed five-arched-links-per-row bracelet, this 42 mm Moonwatch in stainless steel features hesalite glass on the front and an embossed Seahorse medallion on the caseback. Inspired by the 4th generation Speedmaster style worn on the moon, it also includes an asymmetrical case, black step dial and the famous dot over 90 on the anodised aluminium bezel ring.",
            lugToLug = 47.5f,
            thickness = 13.2f,
            caseDiameter = 42f,
            caseMaterial = "Steel",
            dialColor = "Black",
            crystal = "Hesalite crystal with OMEGA logo in the centre",
            waterResistance = "50 m",
            movement = "CALIBRE OMEGA 3861",
            powerReserve = 50,
            bracelet = "Steel",
            openCaseBack = "No"
        ),
        Watch(
            id = 6,
            image = "/images/tsuyosa.jpg",
            rating = 4.0,
            brand = "Citizen",
            model = "Tsuyosa-NJ0150-56L",
            description = " Introducing a sleek new timepiece that brings ultramodern sports styling right to your wrist, meet the Tsuyosa Automatic. Delivered in a versatile 40mm case, the sophisticated design of the watch makes a strong impression via its stainless steel case and a seamlessly integrated, matching bracelet.",
            lugToLug = 45f,
            thickness = 11.7f,
            caseDiameter = 40f,
            caseMaterial = "Stainless Steel",
            dialColor = "Blue",
            crystal = "Sapphire Crystal",
            waterResistance = "50 m",
            movement = "8210",
            powerReserve = 42,
            bracelet = "Stainless Steel",
            openCaseBack = "No"
        )
    )
    private var page3 = listOf(
        Watch(
            id = 7,
            image = "/images/blackbay.jpg",
            rating = 4.3,
            brand = "Tudor",
            model = "Black bay 58",
            description = "TUDOR pays tribute to the brand's first divers' watches with the Black Bay 58, a model whose dimensions evoke that of the epoch. Fitted with a TUDOR Manufacture Calibre, the Black Bay 58 is designed for slim wrists, people who like more compact watches and vintage enthusiasts alike.",
            lugToLug = 48f,
            thickness = 11.9f,
            caseDiameter = 39f,
            caseMaterial = "Steel",
            dialColor = "Black",
            crystal = "Domed sapphire crystal",
            waterResistance = "200 m",
            movement = "Calibre MT5402 (COSC)",
            powerReserve = 70,
            bracelet = "Steel",
            openCaseBack = "No"
        ),
        Watch(
            id = 8,
            image = "/images/snk.jpg",
            rating = 5.0,
            brand = "Seiko",
            model = "SNK809",
            description = "This design is an homage of the German Flieger pilots watches of the Second World War. However due to its smaller size and intended use, this watch feels more at home claiming it as a field tool watch. This watch also has all the great proprietary Seiko features, resulting in an attractive overall package.",
            lugToLug = 43f,
            thickness = 11f,
            caseDiameter = 37f,
            caseMaterial = "Steel",
            dialColor = "Green",
            crystal = "Hardlex",
            waterResistance = "30 m",
            movement = "Seiko 7S26",
            powerReserve = 40,
            bracelet = "Nato strap",
            openCaseBack = "Yes"
        ),
        Watch(
            id = 9,
            image = "/images/prx.jpg",
            rating = 3.6,
            brand = "Tissot",
            model = "Tissot PRX",
            description = "In 1978 the Tissot PRX was born, in 2021 we celebrate its comeback. The new Tissot PRX, the watch for those with passion for design and an eye for ingenuity. Discover the brand new Tissot PRX now, its evocative and slim design makes it an uncompromising essential for all the design enthusiasts.",
            lugToLug = 39f,
            thickness = 10.4f,
            caseDiameter = 40f,
            caseMaterial = "Stainless Steel",
            dialColor = "Blue",
            crystal = "Scratch-resistant sapphire crystal",
            waterResistance = "100 m",
            movement = "Quartz",
            powerReserve = 7,
            bracelet = "Stainless Steel",
            openCaseBack = "No"
        )
    )
    private var page4 = listOf(
        Watch(
            id = 10,
            image = "/images/luminar.jpg",
            rating = 3.4,
            brand = "Panerai",
            model = "Luminor Marina",
            description = "The Panerai Luminor Marina is defined by its cushion-shaped case, crown-protecting bridge, and small seconds dial. The largest models are true statement pieces at 47 mm across. A massive crown guard also catches the eye from a distance. This feature protects the crown against impacts and holds it tightly in the case.",
            lugToLug = 53.7f,
            thickness = 15.65f,
            caseDiameter = 44f,
            caseMaterial = "Steel",
            dialColor = "Black",
            crystal = "Sapphire crystal",
            waterResistance = "100 m",
            movement = "P.6000 calibre",
            powerReserve = 73,
            bracelet = "Caoutchouc Black, STD, 24/22",
            openCaseBack = "No"
        ),
        Watch(
            id = 11,
            image = "/images/superocean.jpg",
            rating = 4.4,
            brand = "Breitling",
            model = "SUPEROCEAN AUTOMATIC 42",
            description = "In 1957, Breitling simultaneously launches two ground-breaking diving watches: a time-only diver (Ref. 1004) and a chronograph (Ref. 807). Both named SuperOcean, these two watches embody two different visions of nautical sports: the elegant time-only (Ref. 1004) is intended for the gentleman who cares about his style even while at the beach, while the sportier chronograph (Ref. 807) is definitively more for the seasoned diver who needs a watch for performance and reliability.",
            lugToLug = 50.5f,
            thickness = 12.5f,
            caseDiameter = 42f,
            caseMaterial = "Steel",
            dialColor = "Black",
            crystal = "Cambered sapphire, glareproofed both sides",
            waterResistance = "300 m",
            movement = "Breitling 17",
            powerReserve = 38,
            bracelet = "Rubber strap",
            openCaseBack = "No"
        ),
        Watch(
            id = 12,
            image = "/images/cartier.jpg",
            rating = 3.6,
            brand = "Cartier",
            model = "Tank",
            description = "The Tank is a line of watches made by Cartier. It was created by Louis Cartier in 1917, and inspired by the new Renault tanks which Cartier saw in use on the Western Front. The prototype watch was presented by Cartier to General John J. Pershing of the American Expeditionary Force.",
            lugToLug = 33.7f,
            thickness = 6.33f,
            caseDiameter = 33.7f,
            caseMaterial = "Yellow gold 750/100",
            dialColor = "Yellow",
            crystal = "Sapphire crystal",
            waterResistance = "30 m",
            movement = "Quartz",
            powerReserve = 7,
            bracelet = "Leather strap",
            openCaseBack = "No"
        )
    )
    private var page5 = listOf(
        Watch(
            id = 13,
            image = "/images/conquest.jpg",
            rating = 3.9,
            brand = "Longines",
            model = "CONQUEST",
            description = "Swiss luxury watch manufacturer Longines has been producing the Conquest since 1954. Originally designed as an elegant dress watch, the collection eventually evolved into a line of sports watches. However, modern timepieces retain much of the original's elegance and still look great paired with a suit and tie.",
            lugToLug = 40.1f,
            thickness = 10.9f,
            caseDiameter = 41f,
            caseMaterial = "Stainless steel",
            dialColor = "Blue",
            crystal = "Sapphire crystal",
            waterResistance = "100 m",
            movement = "L888 calibre",
            powerReserve = 72,
            bracelet = "Stainless steel",
            openCaseBack = "No"
        ),
        Watch(
            id = 14,
            image = "/images/ap.jpg",
            rating = 5.0,
            brand = "Audemars Piguet",
            model = "ROYAL OAK",
            description = "his “Jumbo” model accentuates the Royal Oak’s original codes with a yellow gold case and bracelet enhanced with a smoked yellow-gold-toned “Petite Tapisserie” dial. It houses the latest selfwinding movement, Calibre 7121.",
            lugToLug = 50.5f,
            thickness = 8.1f,
            caseDiameter = 39f,
            caseMaterial = "18-carat yellow gold",
            dialColor = "Yellow",
            crystal = "Sapphire crystal",
            waterResistance = "50 m",
            movement = "CALIBRE 7121",
            powerReserve = 52,
            bracelet = "18-carat yellow gold bracelet with AP folding clasp",
            openCaseBack = "No"
        ),
        Watch(
            id = 15,
            image = "/images/hamilton.jpg",
            rating = 4.3,
            brand = "Hamilton",
            model = "KHAKI FIELD MECHANICAL 38MM",
            description = "The Khaki Field collection includes classic army style watches that have been tried and tested over time. Rugged, robust and resilient, they're ready for the outdoors and can compete with the best sport watches out there. Built for life's adventurers, they're guaranteed to be equal to your needs out in the field.",
            lugToLug = 47f,
            thickness = 9.5f,
            caseDiameter = 38f,
            caseMaterial = "Stainless steel",
            dialColor = "White",
            crystal = "Sapphire crystal",
            waterResistance = "50 m",
            movement = " H-50",
            powerReserve = 80,
            bracelet = "Nato strap",
            openCaseBack = "Yes"
        )
    )

    fun clearData(){
        page1 = emptyList()
    }

    private var exception = false
    fun addException(){
        exception = true
    }
    override suspend fun getAllWatches(page: Int): ApiResponse {
        if(exception){
            throw IOException()
        }
        require(page in 1..5)
        return ApiResponse(
            success = true,
            message = "ok",
            prevPage = calculatePage(page = page)["prevPage"],
            nextPage = calculatePage(page = page)["nextPage"],
            watches = watches[page]!!
        )
    }

    override suspend fun searchWatches(model: String): ApiResponse {
        return ApiResponse(
            success = false
        )
    }


    private fun calculatePage(page: Int): Map<String, Int?> {
        if (page1.isEmpty()) {
            return mapOf("prevPage" to null, "nextPage" to null)
        }
        var prevPage: Int? = page
        var nextPage: Int? = page
        if (page in 1..4) {
            nextPage = nextPage?.plus(1)
        }
        if (page in 2..5) {
            prevPage = prevPage?.minus(1)
        }
        if (page == 1) {
            prevPage = null
        }
        if (page == 5) {
            nextPage = null
        }
        return mapOf("prevPage" to prevPage, "nextPage" to nextPage)
    }
}