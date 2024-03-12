package com.kumcompany.uptime.domain.model

import androidx.annotation.DrawableRes
import com.kumcompany.uptime.R

sealed class OnBoardingPage (
    @DrawableRes
    val image: Int,
    val title : String,
    val description : String
){
    object First: OnBoardingPage(
        image = R.drawable.welcome,
        title = "Greetings",
        description = "Are you into watches?If you are we have great news for you!"
    )
    object Second: OnBoardingPage(
        image = R.drawable.search,
        title = "Explore",
        description = "Find new watch models and learn something new about them!"
    )
    object Third: OnBoardingPage(
        image = R.drawable.time,
        title = "Watch Quote",
        description = "`You can’t turn back the clock. But you can wind it up again.`"
    )
}