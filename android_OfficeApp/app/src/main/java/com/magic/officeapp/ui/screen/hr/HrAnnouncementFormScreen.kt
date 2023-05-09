package com.magic.officeapp.ui.screen.hr

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.TextInput
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.magic.officeapp.ui.component.CustomButton
import java.util.Calendar
import java.util.Date

@Composable
fun HrAnnouncementFormScreen(
    navController: NavController = rememberNavController()
) {
    var (title, setTitle) = remember { mutableStateOf("") }
    var (description, setDescription) = remember { mutableStateOf("") }
    var (date, setDate) = remember { mutableStateOf("") }
    var (role, setRole) = remember { mutableStateOf("") }

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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        item {
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
                    text = "Make an Announcement",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .padding(top = 24.dp)
            ) {
                TextInput(
                    onValueChange = {
                        setTitle(it)
                    },
                    modifier = Modifier
                        .padding(bottom=16.dp),
                    label = "Annouchment Title",
                    type = "text",
                    value = title,
                    placeholder = "Daily Meetings",
                )
                TextInput(
                    onValueChange = {
                        setDescription(it)
                    },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .height(150.dp),
                    label = "Annouchment Description",
                    type = "text",
                    value = description,
                    placeholder = "Enter announcement details",
                )
                TextInput(
                    value = date,
                    onValueChange = setDate,
                    label = "Date",
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

                Spacer(modifier = Modifier.height(16.dp))

                TextInput(
                    onValueChange = {
                        setRole(it)
                    },
                    modifier = Modifier
                        .padding(bottom=16.dp),
                    label = "Role",
                    type = "text",
                    value = role,
                    placeholder = "Developer, Designer",
                )
                Spacer(modifier = Modifier.height(32.dp))
                CustomButton(
                    onClick = {

                    },
                    text = "Submit",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HrAnnouncementFormScreenPreview() {
    HrAnnouncementFormScreen()
}