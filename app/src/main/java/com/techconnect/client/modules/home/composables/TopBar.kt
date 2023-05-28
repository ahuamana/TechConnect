package com.techconnect.client.modules.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techconnect.client.R
import com.techconnect.client.ui.theme.TechConnectTheme

//top bar composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCustom() {
    TopAppBar(
        modifier = Modifier.background(color = Color.Magenta),

        title = {
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = "TechConnect",
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,)
        },
        navigationIcon = {
            Row(
                modifier = Modifier.size(30.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu"
                )
            }

        },
        actions = {
            /*Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search"
            )*/
        }
    )

}

@Preview
@Composable
fun TopBarCustomPrev() {
    TechConnectTheme() {
        TopBarCustom()
    }
}