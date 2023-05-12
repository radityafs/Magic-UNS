package com.magic.officeapp.ui.screen.employee

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.data.model.response.announcement.DataItem
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.viewmodel.AnnouncementViewModel
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.utils.constants.Result
import com.magic.officeapp.utils.timeToText

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnnouncementScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    announcementViewModel: AnnouncementViewModel = hiltViewModel(),
) {

    val user = authViewModel.userData.collectAsState().value
    val announcement = announcementViewModel.userAnnouncementState.collectAsState().value
    var listAnnouncement: List<DataItem> = emptyList()

    if (user?.jwt != null) {
        announcementViewModel.getAnnouncementUser(user.id.toString(), user.jobRole?.id.toString())
    }

    when (announcement) {
        is Result.Success -> {
            val data = announcement.data.data
            listAnnouncement = data as List<DataItem>
        }
        is Result.Error -> {
            Log.d("TAG", "AnnouncementScreen: ${announcement.message}")
        }
        else -> {

        }
    }



    LazyColumn(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxSize()
    ) {
        item {
            Row(modifier = Modifier.padding(top = 52.dp)) {
                Text(text = "Announcement", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(36.dp))
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Message", fontWeight = FontWeight.Bold, fontSize = 14.sp)

                CustomButton(
                    modifier = Modifier
                        .height(32.dp)
                        .width(95.dp),
                    onClick = { /*TODO*/ },
                    text = "Filter",
                    IconButton = R.drawable.ic_outline_filter_list
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
        }

        listAnnouncement.map { announcement ->
            item {
                Column(
                    modifier = Modifier
                        .padding(
                            top = 12.dp
                        )
                        .border(
                            width = 1.dp,
                            color = Color("#E5E5E5".toColorInt()),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (announcement.attributes?.state == null) "Announcement" else announcement.attributes.state,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color("#858D9D".toColorInt())
                        )

                        Text(
                            text = timeToText(announcement.attributes?.createdAt ?: "2021-09-09T09:09:09.000000Z"),
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color("#858D9D".toColorInt())
                        )
                    }

                    Row(
                        modifier = Modifier.padding(top = 13.dp, bottom = 4.dp)
                    ) {
                        Text(
                            text = announcement.attributes?.title ?: "Title",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                        )
                    }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = announcement.attributes?.description ?: "Description",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color("#858D9D".toColorInt())
                        )
                    }

                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewAnnouncementScreen() {
    AnnouncementScreen()
}