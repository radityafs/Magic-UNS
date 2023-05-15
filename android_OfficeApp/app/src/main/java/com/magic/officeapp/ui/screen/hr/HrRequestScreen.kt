package com.magic.officeapp.ui.screen.hr

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.data.model.response.announcement.DataItem
import com.magic.officeapp.data.model.response.request.GetAllRequestsDataItem
import com.magic.officeapp.ui.component.CardRequests
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.component.TextInput
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.viewmodel.RequestViewModel
import com.magic.officeapp.utils.constants.Result

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HrRequestScreen(
    navController: NavController = rememberNavController(),
    requestViewModel: RequestViewModel = hiltViewModel()
) {
    var (search, setSearch) = remember {
        mutableStateOf("")
    }
    var (acceptedRequest, setAcceptedRequest) = remember {
        mutableStateOf(0)
    }
    var (pendingRequest, setPendingRequest) = remember {
        mutableStateOf(0)
    }
    var (rejectedRequest, setRejectedRequest) = remember {
        mutableStateOf(0)
    }
    var requests = requestViewModel.getAllRequestsResponse.collectAsState().value
    var data :  List<GetAllRequestsDataItem> = emptyList()
    requestViewModel.getAllRequests()
    when (requests) {
        is Result.Success -> {
            data = requests.data.data as List<GetAllRequestsDataItem>

            setAcceptedRequest(data.filter { it.attributes?.isApproved == "approved" }.size)
            setPendingRequest(data.filter { it.attributes?.isApproved == "waiting" }.size)
            setRejectedRequest(data.filter { it.attributes?.isApproved == "rejected" }.size)
        }
        is Result.Error -> {
            Log.d("TAG", "AnnouncementScreen: ${requests.message}")
        }
        else -> {

        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .padding(top = 52.dp)
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
                    text = "Requests Activity",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color("#E5E5E5".toColorInt()), RoundedCornerShape(6.dp))
                    .padding(top = 21.dp, bottom = 21.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = acceptedRequest.toString(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#37A345".toColorInt())
                    )
                    Text(
                        text = "Accepted",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#858D9D".toColorInt())
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = pendingRequest.toString(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#E5B200".toColorInt())
                    )
                    Text(
                        text = "Waiting",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#858D9D".toColorInt())
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = rejectedRequest.toString(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#D3221E".toColorInt())
                    )
                    Text(
                        text = "Rejected",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#858D9D".toColorInt())
                    )
                }
            }


            Row(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Request",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )

                CustomButton(
                    modifier = Modifier
                        .height(32.dp)
                        .width(95.dp),
                    onClick = {
                    },
                    text = "Filter",
                    IconButton = R.drawable.ic_outline_filter_list
                )
            }
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
        data.map { request ->
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    CardRequests(
                        title = if (request.attributes?.requestType == "permit") "Request Permit" else "Other Request",
                        created_at = request.attributes?.createdAt.toString(),
                        Status = request.attributes?.isApproved.toString(),
                        onClick = {
                            navController.navigate(Screen.HrRequestDetailScreen.route+ "/${request.id}")
                        },
                        requestType = request.attributes?.requestType.toString(),
                    )

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp),
                        color = Color("#F0F1F3".toColorInt())
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HrRequestScreen() {
    HrAnnouncementScreen()
}