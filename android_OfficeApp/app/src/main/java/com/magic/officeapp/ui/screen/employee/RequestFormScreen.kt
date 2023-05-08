package com.magic.officeapp.ui.screen.employee

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.component.TextInput
import com.magic.officeapp.ui.navigation.Screen
import java.util.*


@Composable
fun RequestFormScreen(
    navController: NavController = rememberNavController(),
) {

    val (selectedType, setSelectedType) = remember { mutableStateOf("permit") }
    val (description, setDescription) = remember { mutableStateOf("") }
    val (date, setDate) = remember { mutableStateOf("") }

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext, { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            setDate("$mDayOfMonth/${mMonth + 1}/$mYear")
        }, mYear, mMonth, mDay
    )

    fun isButtonSelected(
        type: String
    ): Modifier {
        return if (type == selectedType) {
            Modifier
                .height(40.dp)
                .border(
                    width = 1.dp,
                    color = Color("#93EFAA".toColorInt()),
                    shape = RoundedCornerShape(6.dp)
                )
        } else {
            Modifier
                .height(40.dp)
                .border(
                    width = 1.dp,
                    color = Color("#F0F1F3".toColorInt()),
                    shape = RoundedCornerShape(6.dp)
                )
        }
    }

    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(top = 52.dp)
                .fillMaxWidth()
                .clickable {
                    navController.popBackStack()
                }, verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "Back Icon",
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Request Form",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column() {
            Text(
                text = "Request Type",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                CustomButton(
                    onClick = {
                        setSelectedType("permit")
                    },
                    modifier = isButtonSelected("permit").weight(1f),
                    backgroundColor = if (selectedType == "permit") Color("#DFFCE6".toColorInt()) else Color(
                        "#F0F1F3".toColorInt()
                    ),
                    contentColor = if (selectedType == "permit") Color("#37A345".toColorInt()) else Color(
                        "#A3A9B6".toColorInt()
                    ),
                    text = "Permit",
                )

                Spacer(modifier = Modifier.width(16.dp))

                CustomButton(
                    onClick = {
                        setSelectedType("other")
                    },
                    modifier = isButtonSelected("other").weight(1f),
                    backgroundColor = if (selectedType == "other") Color("#DFFCE6".toColorInt()) else Color(
                        "#F0F1F3".toColorInt()
                    ),
                    contentColor = if (selectedType == "other") Color("#37A345".toColorInt()) else Color(
                        "#A3A9B6".toColorInt()
                    ),
                    text = "Other"
                )
            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Description",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TextInput(
                value = description,
                onValueChange = setDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                placeholder = "Enter Description",
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (selectedType == "permit") {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Choose Date",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            TextInput(
                value = date,
                onValueChange = setDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        mDatePickerDialog.show()
                    },
                enabled = false,
                placeholder = "DD/MM/YYYY",
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            CustomButton(
                onClick = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.HomeScreen.route) {
                            inclusive = true
                        }
                    }
                }, text = "Request", modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RequestFormScreenPreview() {
    RequestFormScreen()
}