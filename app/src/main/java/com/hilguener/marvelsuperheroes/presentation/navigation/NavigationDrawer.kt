package com.hilguener.marvelsuperheroes.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hilguener.marvelsuperheroes.R

@Composable
fun DrawerHeader() {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center,
    ) {
        Image(painter = painterResource(id = R.drawable.marvel_header), contentDescription = null)
    }
}

@Composable
fun DrawerBody(
    items: List<NavDrawerItem>,
    onItemClick: (NavDrawerItem) -> Unit,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(item) }
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp)),
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}
