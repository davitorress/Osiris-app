package com.papaya.osiris.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.papaya.osiris.R
import com.papaya.osiris.ui.components.*
import com.papaya.osiris.ui.theme.MediumGreen
import com.papaya.osiris.ui.theme.OsirisTheme
import com.papaya.osiris.ui.theme.White

@Composable
fun RegisterPage() {
    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var confirmarSenha by rememberSaveable { mutableStateOf("") }
    var isFarmer by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(vertical = 0.dp, horizontal =  22.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 96.dp, bottom = 48.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.osiris_logo),
                contentDescription = "Osiris",
                alignment = Alignment.Center
            )

            Column(verticalArrangement = Arrangement.spacedBy(26.dp)) {
                TextInput(
                    text = nome,
                    label = "Nome completo:",
                    onTextChange = { nome = it },
                    placeholder = "Digite o seu nome completo"
                )
                EmailInput(
                    text = email,
                    onTextChange = { email = it }
                )
                PasswordInput(
                    text = senha,
                    onTextChange = { senha = it }
                )
                PasswordInput(
                    text = confirmarSenha,
                    onTextChange = { confirmarSenha = it },
                    label = "Confirmar senha:",
                    placeholder = "Digite novamente a sua senha"
                )
                CheckboxInput(
                    text = "Sou um agricultor",
                    checked = isFarmer,
                    onCheckedChange = { isFarmer = it }
                )
            }

            ThemedButton(
                onClick = {  },
                theme = ButtonTheme.Medium,
                text = "Cadastrar"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start)
            ) {
                Text(
                    text = "Já possui uma conta?",
                    color = MediumGreen,
                    style = MaterialTheme.typography.labelSmall
                )
                ThemedTextButton(
                    onClick = {  },
                    text = "Entre aqui",
                    theme = ButtonTheme.Wine,
                    modifier = Modifier,
                    fontWeight = FontWeight.Black,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun RegisterPagePreview() {
    OsirisTheme {
        RegisterPage()
    }
}