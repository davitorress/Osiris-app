package com.papaya.osiris.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.rounded.Search
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.papaya.osiris.ui.theme.*

@Composable
fun TextInput(
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (!label.isNullOrBlank()) {
            Text(
                text = label,
                color = MediumGreen,
                style = MaterialTheme.typography.labelLarge
            )
        }
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
        modifier = modifier.fillMaxWidth().padding(0.dp),
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

@Composable
fun SearchInput(
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder,
                color = GrayBorder,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelSmall
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Black,
            cursorColor = GrayBorder,
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            focusedBorderColor = GrayBorder,
            unfocusedBorderColor = GrayBorder,
        ),
        textStyle = MaterialTheme.typography.labelSmall.copy(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        ),
        leadingIcon = {
            Image(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                colorFilter = ColorFilter.tint(LightGreen)
            )
        },
        shape = RoundedCornerShape(percent = 99),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun TextAreaInput(
    label: String,
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
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
            singleLine = false,
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
            modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 100.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectInput(
    text: String,
    onTextChange: (index: Int, String) -> Unit,
    options: List<String>,
) {
    var exposed by rememberSaveable { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = exposed,
        onExpandedChange = { exposed = it },
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Black,
                cursorColor = Black,
                focusedContainerColor = Gray,
                unfocusedContainerColor = Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTrailingIconColor = LightGreen,
                unfocusedTrailingIconColor = LightGreen,
            ),
            textStyle = MaterialTheme.typography.labelSmall,
            trailingIcon = {
                Icon(imageVector = Icons.Filled.ExpandMore, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth().menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = exposed,
            onDismissRequest = { exposed = false },
            modifier = Modifier.fillMaxWidth(),
        ) {
            options.forEachIndexed { index, it ->
                DropdownMenuItem(
                    onClick = {
                        onTextChange(index, it)
                        exposed = false
                    },
                    text = {
                        Text(
                            text = it,
                            color = Black,
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun MultiInput(
    label: String,
    modifier: Modifier = Modifier,
    actionAdd: () -> Unit,
    actionRemove: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = label,
            color = MediumGreen,
            style = MaterialTheme.typography.labelLarge
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            content()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
        ) {
            ThemedButton(
                onClick = actionRemove,
                theme = ButtonTheme.Wine,
                icon = Icons.Filled.RemoveCircleOutline,
            )
            ThemedButton(
                onClick = actionAdd,
                theme = ButtonTheme.Light,
                icon = Icons.Filled.AddCircleOutline,
            )
        }
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

@Preview(showBackground = true, widthDp = 400)
@Composable
fun SearchInputPreview() {
    var text by rememberSaveable { mutableStateOf("") }

    OsirisTheme {
        SearchInput(
            text = text,
            onTextChange = { text = it },
            placeholder = "Busque por plantas ou receitas"
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun TextAreaInputPreview() {
    var text by rememberSaveable { mutableStateOf("") }

    OsirisTheme {
        TextAreaInput(
            label = "Textarea",
            text = text,
            onTextChange = { text = it },
            placeholder = "Digite um texto grande",
        )
    }
}
