package com.magic.officeapp.ui.screen.hr

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import com.magic.officeapp.data.model.response.announcement.DataItem
import com.magic.officeapp.ui.viewmodel.AnnouncementViewModel
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.utils.constants.Result
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HrAnnouncementScreen(
    navController: NavController = rememberNavController(),
    announcementViewModel : AnnouncementViewModel = hiltViewModel(),
) {
    var (search, setSearch) = remember {
        mutableStateOf("")
    }

    val announcement = announcementViewModel.announcementData.collectAsState().value
    var listAnnouncement: List<DataItem> = emptyList()
    announcementViewModel.getAllAnnouncement()

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

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            CustomButton(
                onClick = {
                    navController.navigate(Screen.HrAnnouncementFormScreen.route)
                },
                text = "Make an Announcement",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(start = 24.dp, end = 24.dp)
            )
        },

        ) {
        LazyColumn(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
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
                        text = "Announcement",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recent Announcement",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    CustomButton(
                        modifier = Modifier
                            .height(32.dp)
                            .width(95.dp),
                        onClick = { /*TODO*/ },
                        text = "Filter",
                        IconButton = R.drawable.ic_outline_filter_list
                    )
                }
            }

            item {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    TextInput(
                        value = search,
                        onValueChange = {
                            setSearch(it)
                        },
                        placeholder = "Search Item",
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon"
                            )
                        }
                    )
                }
            }

            listAnnouncement.map { announcement ->
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp, bottom = 12.dp),
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = announcement.attributes?.date.toString(),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                    color = Color("#858D9D".toColorInt())
                                )


                                Text(
                                    announcement.attributes?.title.toString(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color("#292D35".toColorInt()),
                                    modifier = Modifier.padding(top = 12.dp)
                                )

                                Text(
                                    announcement.attributes?.description.toString(),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                    color = Color("#858D9D".toColorInt()),
                                    modifier = Modifier.padding(top = 5.dp)
                                )

                                Text(
                                    "To :" + announcement.attributes?.jobRoles?.data?.get(0)?.attributes?.name.toString(),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                    color = Color("#37A345".toColorInt()),
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnnouncementScreen() {
    HrAnnouncementScreen()
}

