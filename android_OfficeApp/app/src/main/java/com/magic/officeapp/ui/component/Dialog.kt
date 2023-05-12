package com.magic.officeapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.magic.officeapp.R
import com.magic.officeapp.ui.theme.Green100
import com.magic.officeapp.ui.theme.Red600

@Composable
fun Dialog(
    show: Boolean,
    icon: Int,
    iconBackgroundColor: Color = Color.Transparent,
    title: String = "Dialog Title",
    message: String,
    onDismissAction: (Boolean) -> Unit = {},
    onConfirmAction: (Boolean) -> Unit,
    onCancelAction: (() -> Unit)? = null,
    backgroundButton: Color = Color("#2C803A".toColorInt()),
) {
    if (show) {
        AlertDialog(
            modifier = Modifier
                .padding(16.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            onDismissRequest = {
                onDismissAction(false)
            },
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomIcon(icon = icon, size = 48, backgroundColor = iconBackgroundColor)
                }

            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        color = Color("#292D35".toColorInt()),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = message,
                        color = Color("#667085".toColorInt()),
                        modifier = Modifier.padding(top = 12.dp),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                    ) {

                        if (onCancelAction != null) {
                            CustomButton(
                                onClick = {
                                    onCancelAction()
                                }, text = "Cancel",
                                contentColor = Color("#292D35".toColorInt()),
                                backgroundColor = Color("#E5E5E5".toColorInt()),
                                modifier = Modifier
                                    .height(35.dp)
                                    .weight(1f)
                                    .clip(shape = RoundedCornerShape(72.dp))
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                        }

                        CustomButton(
                            onClick = {
                                onConfirmAction(false)
                            },
                            text = "Okay",
                            backgroundColor = backgroundButton,
                            modifier = Modifier
                                .height(35.dp)
                                .weight(1f)
                                .clip(shape = RoundedCornerShape(72.dp)),
                        )


                    }
                }
            },
            confirmButton = {},
            dismissButton = {},
            backgroundColor = Color.White
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    val (show, setShow) = remember {
        mutableStateOf(true)
    }

    Dialog(
        show = show,
        icon = R.drawable.state_success,
        iconBackgroundColor = Green100,
        title = "Check In Success",
        message = "Let's work together to achieve our goals and make a positive impact. We got this!",
        onDismissAction = {
            setShow(it)
        },
        onConfirmAction = {
            setShow(it)
        },
        backgroundButton = Red600,
    )
}