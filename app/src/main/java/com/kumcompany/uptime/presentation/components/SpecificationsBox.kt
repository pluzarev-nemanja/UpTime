package com.kumcompany.uptime.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kumcompany.uptime.R
import com.kumcompany.uptime.domain.model.Watch

@Composable
fun SpecificationsBoxes(
    selectedWatch: Watch,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onBackground
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = "More details",
            fontSize = MaterialTheme.typography.h6.fontSize,
            color = contentColor,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            SpecificationItem(
                bigText = selectedWatch.brand,
                smallText = "brand",
                icon = R.drawable.brand,
                infoBoxIconColor, contentColor
            )
            SpecificationItem(
                bigText = selectedWatch.lugToLug.toString(),
                smallText = "lug to lug",
                icon = R.drawable.casediameter,
                infoBoxIconColor, contentColor

            )
            SpecificationItem(
                bigText = selectedWatch.openCaseBack,
                smallText = "open back",
                icon = R.drawable.time,
                infoBoxIconColor, contentColor

            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            SpecificationItem(
                bigText = selectedWatch.thickness.toString(),
                smallText = "thickness",
                icon = R.drawable.casediameter,
                infoBoxIconColor, contentColor

            )
            SpecificationItem(
                bigText = selectedWatch.caseMaterial,
                smallText = "case",
                icon = R.drawable.time,
                infoBoxIconColor, contentColor

            )
            SpecificationItem(
                bigText = selectedWatch.dialColor,
                smallText = "dial color",
                icon = R.drawable.time,
                infoBoxIconColor, contentColor

            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            SpecificationItem(
                bigText = selectedWatch.crystal,
                smallText = "crystal",
                icon = R.drawable.crystal,
                infoBoxIconColor, contentColor

            )
            SpecificationItem(
                bigText = selectedWatch.bracelet,
                smallText = "bracelet",
                icon = R.drawable.bracelet,
                infoBoxIconColor, contentColor

            )
        }
    }
}

@Composable
fun SpecificationItem(
    bigText: String,
    smallText: String,
    icon: Int,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onBackground
) {

    Box(
        modifier = Modifier
            .padding(5.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(14.dp)),
        contentAlignment = Alignment.Center
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 6.dp)
                    .size(30.dp),
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = infoBoxIconColor
            )
            Column {
                Text(
                    text = bigText,
                    color = contentColor,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = smallText,
                    color = contentColor,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                )
            }
        }
    }

}