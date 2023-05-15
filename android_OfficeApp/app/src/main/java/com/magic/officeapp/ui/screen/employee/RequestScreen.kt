package com.magic.officeapp.ui.screen.employee

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.viewmodel.AuthViewModel
import com.magic.officeapp.ui.viewmodel.RequestViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.magic.officeapp.data.model.response.request.DataItem
import com.magic.officeapp.ui.component.CardRequests
import com.magic.officeapp.utils.constants.Result


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RequestScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    requestViewModel: RequestViewModel = hiltViewModel()
) {

    var requestList = emptyList<DataItem>()

    val requestData = requestViewModel.requestDataState.collectAsState().value
    val acceptedRequest = remember { mutableStateOf(0) }
    val pendingRequest = remember { mutableStateOf(0) }
    val rejectedRequest = remember { mutableStateOf(0) }

    val user = authViewModel.userData.collectAsState().value

    if (user?.jwt != null) {
        requestViewModel.getUserRequest(user.id.toString())
    }

    when (requestData) {
        is Result.Success -> {
            val data = requestData.data.data
            requestList = data as List<DataItem>

            acceptedRequest.value = data.filter { it.attributes?.isApproved == "approved" }.size
            pendingRequest.value = data.filter { it.attributes?.isApproved == "waiting" }.size
            rejectedRequest.value = data.filter { it.attributes?.isApproved == "rejected" }.size
        }
        is Result.Error -> {
            Log.d("TAG", "RequestScreen: ${requestData.message}")
        }
        else -> {
            Log.d("TAG", "RequestScreen: $requestData")
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            CustomButton(
                onClick = {
                    navController.navigate(Screen.RequestFormScreen.route)
                },
                text = "Make a Request",
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .height(45.dp)
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        LazyColumn(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 50.dp)
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
                        text = "Request",
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
                            text = acceptedRequest.value.toString(),
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
                            text = pendingRequest.value.toString(),
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
                            text = rejectedRequest.value.toString(),
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
                        text = "Recent Request",
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

            requestList.map { request ->
                item(key = request.id) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        CardRequests(
                            title = if (request.attributes?.requestType == "permit") "Request Permit" else "Other Request",
                            created_at = request.attributes?.createdAt!!,
                            Status = request.attributes?.isApproved!!,
                            onClick = {
                                navController.navigate(Screen.RequestDetailScreen.route + "/${request.id}")
                            },
                            requestType = request.attributes?.requestType!!
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

            item {
                Spacer(modifier = Modifier.padding(bottom = 100.dp))
            }
        }
    }
}



