package com.papaya.osiris.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.papaya.osiris.ui.theme.*

@Composable
fun TextInput(
    label: String,
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            color = MediumGreen,
            style = MaterialTheme.typography.labelLarge
        )
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            singleLine = true,
            placeholder = {
                Text(
                    text = placeholder,
                    color = GrayBorder,
                    style = MaterialTheme.typography.labelMedium
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Black,
                cursorColor = Black,
                focusedContainerColor = Gray,
                unfocusedContainerColor = Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            textStyle = MaterialTheme.typography.labelMedium,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PasswordInput(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Senha:",
    placeholder: String = "Digite a sua senha",
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            color = MediumGreen,
            style = MaterialTheme.typography.labelLarge
        )
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            singleLine = true,
            placeholder = {
                Text(
                    text = placeholder,
                    color = GrayBorder,
                    style = MaterialTheme.typography.labelMedium
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Default,
            ),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Black,
                cursorColor = Black,
                focusedContainerColor = Gray,
                unfocusedContainerColor = Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            textStyle = MaterialTheme.typography.labelMedium,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun EmailInput(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "E-mail:",
    placeholder: String = "Digite o seu e-mail",
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            color = MediumGreen,
            style = MaterialTheme.typography.labelLarge
        )
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            singleLine = true,
            placeholder = {
                Text(
                    text = placeholder,
                    color = GrayBorder,
                    style = MaterialTheme.typography.labelMedium
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Default,
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Black,
                cursorColor = Black,
                focusedContainerColor = Gray,
                unfocusedContainerColor = Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            textStyle = MaterialTheme.typography.labelMedium,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CheckboxInput(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MediumGreen,
                checkmarkColor = White,
                uncheckedColor = GrayBorder
            ),
            modifier = Modifier.clip(MaterialTheme.shapes.medium).padding(0.dp)
        )
        Text(
            text = text,
            color = MediumGreen,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun InputPreview() {
    var text by rememberSaveable { mutableStateOf("") }

    OsirisTheme {
        TextInput(
            label = "Input:",
            text = text,
            placeholder = "Digite um texto",
            onTextChange = { text = it }
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun CheckboxPreview() {
    var checked by rememberSaveable { mutableStateOf(false) }

    OsirisTheme {
        CheckboxInput(
            text = "Selecione a opção",
            checked = checked,
            onCheckedChange = { checked = it }
        )
    }
}
