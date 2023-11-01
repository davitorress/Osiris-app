package com.papaya.osiris.ui.components

import androidx.compose.foundation.Canvas
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
fun BulletList(
    items: List<String>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        items.forEach {
            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(
                    modifier = Modifier
                        .size(4.dp)
                ){
                    drawCircle(Black)
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = it,
                    color = Black,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 375)
@Composable
fun BulletListPreview() {
    OsirisTheme {
        BulletList(
            listOf(
                "Texto 1",
                "Texto 2",
                "Texto 3",
                "Texto 4",
                "Texto 5",
                "Texto 6",
            )
        )
    }
}