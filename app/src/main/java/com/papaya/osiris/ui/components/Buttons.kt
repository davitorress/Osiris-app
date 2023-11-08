package com.papaya.osiris.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.papaya.osiris.ui.theme.*

enum class ButtonTheme {
    Light, Medium, Wine
}

@Composable
fun ThemedButton(
    onClick: () -> Unit,
    theme: ButtonTheme,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null,
    circleShape: Boolean = false
) {
    val colors = when(theme) {
        ButtonTheme.Light -> {
            IconButtonDefaults.filledIconButtonColors(
                containerColor = LightGreen,
                contentColor = White
            )
        }
        ButtonTheme.Medium -> {
            IconButtonDefaults.filledIconButtonColors(
                containerColor = MediumGreen,
                contentColor = White
            )
        }
        ButtonTheme.Wine -> {
            IconButtonDefaults.filledIconButtonColors(
                containerColor = Wine,
                contentColor = White
            )
        }
    }

    FilledIconButton(
        onClick = onClick,
        colors = colors,
        shape = if (circleShape) CircleShape else MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            if (text !== null) {
                Text(
                    text = text,
                    modifier = textModifier,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            if (icon !== null) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    }
}

@Composable
fun ThemedTextButton(
    text: String,
    theme: ButtonTheme,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    fontWeight: FontWeight? = null,
    textDecoration: TextDecoration? = null
) {
    val colors = when(theme) {
        ButtonTheme.Light -> {
            ButtonDefaults.textButtonColors(
                contentColor = LightGreen
            )
        }
        ButtonTheme.Medium -> {
            ButtonDefaults.textButtonColors(
                contentColor = MediumGreen
            )
        }
        ButtonTheme.Wine -> {
            ButtonDefaults.textButtonColors(
                contentColor = Wine
            )
        }
    }

    TextButton(
        onClick = onClick,
        colors = colors,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier.wrapContentSize().padding(0.dp)
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        ) {
            Text(
                text = text,
                fontWeight = fontWeight,
                textDecoration = textDecoration,
                style = MaterialTheme.typography.labelSmall,
            )
            if (icon !== null) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun ButtonPreview() {
    OsirisTheme {
        ThemedButton(
            text = "Button",
            onClick = {  },
            theme = ButtonTheme.Medium,
            icon = Icons.Filled.AddCircleOutline
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun TextButtonPreview() {
    OsirisTheme {
        ThemedTextButton(
            text = "Button",
            onClick = {  },
            theme = ButtonTheme.Medium,
            icon = Icons.Filled.AddCircleOutline
        )
    }
}