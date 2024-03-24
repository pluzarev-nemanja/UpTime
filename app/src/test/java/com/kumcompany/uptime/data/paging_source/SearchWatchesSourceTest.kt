package com.kumcompany.uptime.data.paging_source

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingSource.LoadResult.Page
import com.kumcompany.uptime.data.remote.FakeWatchApi
import com.kumcompany.uptime.data.remote.WatchApi
import com.kumcompany.uptime.domain.model.Watch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchWatchesSourceTest {

    private lateinit var watchApi: WatchApi
    private lateinit var watches: List<Watch>


    @Before
    fun setup() {
        watchApi = FakeWatchApi()
        watches = listOf(
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
    }

    @Test
    fun `Search api with existing watch model,expect single watch result, assert LoadResult_Page`() =
        runTest {
            val watchesSource = SearchWatchesSource(watchApi, query = "Cosmograph Daytona")
            assertEquals<LoadResult<Int, Watch>>(
                expected = Page(
                    data = listOf(watches.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = watchesSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )

        }

    @Test
    fun `Search api with existing watch model,expect multiple watch result, assert LoadResult_Page`() =
        runTest {
            val watchesSource = SearchWatchesSource(watchApi, query = "o")
            assertEquals<LoadResult<Int, Watch>>(
                expected = Page(
                    data = listOf(watches.first(), watches[2]),
                    prevKey = null,
                    nextKey = null
                ),
                actual = watchesSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )

        }

    @Test
    fun `Search api with empty watch model,assert empty watches list and LoadResult_Page`() =
        runTest {
            val watchesSource = SearchWatchesSource(watchApi, query = "")
            val loadResult = watchesSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = watchApi.searchWatches("").watches
            assertTrue {
                result.isEmpty()
            }
            assertTrue { loadResult is Page }

        }

    @Test
    fun `Search api with non_existing watch model,assert empty watches list and LoadResult_Page`() =
        runTest {
            val watchesSource = SearchWatchesSource(watchApi, query = "Unknown")
            val loadResult = watchesSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = watchApi.searchWatches("Unknown").watches
            assertTrue {
                result.isEmpty()
            }
            assertTrue { loadResult is Page }

        }
}