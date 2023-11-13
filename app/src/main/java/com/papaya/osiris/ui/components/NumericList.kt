package com.papaya.osiris.ui.components

import androidx.compose.foundation.layout.*
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
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        items.forEachIndexed {index, item ->
            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.Top,
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