package com.magic.officeapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.magic.officeapp.R

@Composable
fun TextInput(
    modifier: Modifier? = null,
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String = "",
    enabled: Boolean = true,
    type: String = "text",
    leadingIcon: @Composable (() -> Unit)? = null,
    isValid: (Boolean) -> Unit = { },
) {

    var textVisible = remember { mutableStateOf(false) }

    val isEmailValid = value.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
    val isNumberValid = value.matches(Regex("[0-9]+"))
    val isPasswordValid = value.length > 8
    val isTextValid = value.length >= 3
    val isValidState = remember { mutableStateOf(true) }

    val isFieldValid = when (type) {
        "email" -> isEmailValid
        "password" -> isPasswordValid
        "text" -> isTextValid
        "number" -> isNumberValid
        else -> true
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        if (label != null) {
            Text(
                modifier = Modifier
                    .padding(bottom = 18.dp)
                    .fillMaxWidth(),
                text = label,
            )
        }

        TextField(
            modifier = modifier?.border(
                width = 1.dp,
                color = if (isValidState.value) Color(15, 13, 35, 51) else Color.Red,
                shape = RoundedCornerShape(10.dp)
            ) ?: Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = if (isValidState.value) Color(15, 13, 35, 51) else Color.Red,
                    shape = RoundedCornerShape(10.dp)
                ),
            visualTransformation = if (!textVisible.value && type == "password") {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            enabled = enabled,
            value = value,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            keyboardOptions = when (type) {
                "email" -> KeyboardOptions(keyboardType = KeyboardType.Email)
                "password" -> KeyboardOptions(keyboardType = KeyboardType.Password)
                "text" -> KeyboardOptions(keyboardType = KeyboardType.Text)
                "number" -> KeyboardOptions(keyboardType = KeyboardType.Number)
                else -> KeyboardOptions(keyboardType = KeyboardType.Text)
            },
            leadingIcon = leadingIcon,
            trailingIcon = if (type == "password") {
                {
                    val image = if (textVisible.value) R.drawable.baseline_visibility_24
                    else R.drawable.baseline_visibility_off_24

                    val description = if (textVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = { textVisible.value = !textVisible.value }) {
                        Icon(painterResource(id = image), contentDescription = description)
                    }
                }
            } else null,
            onValueChange = {
                onValueChange(it)
                isValidState.value = isFieldValid
                isValid(isFieldValid)
            },
            placeholder = {
                if (placeholder.isNotEmpty()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(), text = placeholder
                    )
                }
            },
        )
    }

}


@Preview(showBackground = true)
@Composable
fun TextInputPreview() {
    TextInput(
        onValueChange = {},
        label = "Email",
        type = "text",
        value = "radityafiqa",
        placeholder = "Enter your email",
    )
}