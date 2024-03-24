package com.kumcompany.uptime.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test

class RatingWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun passZeroPointZeroValue_Assert_FiveEmptyHearts() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(10.dp), rating = 0.0)
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyHeart")
            .assertCountEquals(5)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledHeart")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledHeart")
            .assertCountEquals(0)
    }

    @Test
    fun passZeroPointFiveValue_Assert_FourEmptyHeartsAndOneHalfFilledHeart() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(10.dp), rating = 0.5)
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyHeart")
            .assertCountEquals(4)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledHeart")
            .assertCountEquals(1)
        composeTestRule.onAllNodesWithContentDescription("FilledHeart")
            .assertCountEquals(0)
    }

    @Test
    fun passZeroPointSixValue_Assert_FourEmptyHeartsAndOneFilledHeart() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(10.dp), rating = 0.6)
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyHeart")
            .assertCountEquals(4)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledHeart")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledHeart")
            .assertCountEquals(1)
    }

    @Test
    fun passFourPointZeroValue_Assert_FourFilledHearts_And_OneEmptyHeart() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(10.dp), rating = 4.0)
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyHeart")
            .assertCountEquals(1)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledHeart")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledHeart")
            .assertCountEquals(4)
    }


    @Test
    fun passNegativeValue_Assert_FiveEmptyHearts() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(10.dp), rating = -4.3)
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyHeart")
            .assertCountEquals(5)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledHeart")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledHeart")
            .assertCountEquals(0)
    }

    @Test
    fun passInvalidValue_Assert_FiveEmptyHearts() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(10.dp), rating = 8.9)
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyHeart")
            .assertCountEquals(5)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledHeart")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledHeart")
            .assertCountEquals(0)
    }

}