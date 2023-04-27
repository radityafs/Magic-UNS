package com.magic.officeapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    type: String = "text",
    isValid : (Boolean) -> Unit = {  } ,
) {

    val isEmailValid = value.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
    val isNumberValid = value.matches(Regex("[0-9]+"))
    val isPasswordValid = value.length > 8
    val isTextValid = value.length >= 3
    val isValidState = remember { mutableStateOf(true) }

    val isFieldValid = when(type) {
        "email" -> isEmailValid
        "password" -> isPasswordValid
        "text" -> isTextValid
        "number" -> isNumberValid
        else -> true
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()) {

            if (label.isNotEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = label
                )
            }

        }

    Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier.fillMaxWidth().height(50.dp).clip(RoundedCornerShape(10.dp)).border(
                    width = 1.dp,
                    color = if(isValidState.value) Color(15,13,35, 51) else Color.Red,
                    shape = RoundedCornerShape(10.dp)
                ),
                value = value,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                onValueChange = {
                    onValueChange(it)
                    isValidState.value = isFieldValid
                    isValid(isFieldValid)
                },
                placeholder = {
                    if (placeholder.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = placeholder
                        )
                    }
                },
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun TextInputPreview() {
    TextInput(
        onValueChange = {},
        label = "Email",
        type = "text",
        value="radityafiqa",
        placeholder = "Enter your email",
    )
}