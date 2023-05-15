package com.magic.officeapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

@Composable
fun CustomButton(
    onClick: () -> Unit,
    backgroundColor: Color = Color("#333D55".toColorInt()),
    contentColor: Color = Color.White,
    text: String = "Button",
    modifier: Modifier = Modifier,
    IconButton: Int? = null,
    textModifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick, modifier = modifier, colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor, contentColor = contentColor
        )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (IconButton != null) {
                Icon(
                    painter = painterResource(id = IconButton),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 8.dp)
                )
            }
            Text(text = text, modifier = textModifier)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    CustomButton(onClick = { })
}