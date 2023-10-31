package com.papaya.osiris.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.papaya.osiris.ui.theme.Black
import com.papaya.osiris.ui.theme.OsirisTheme

@Composable
fun NumericList(
    items: List<String>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(0.dp),
        contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp)
    ) {
        itemsIndexed(items = items) {index, item ->
            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${index + 1}.",
                    color = Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = item,
                    color = Black,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 375)
@Composable
private fun NumericListPreview() {
    OsirisTheme {
        NumericList(
            listOf(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
            )
        )
    }
}