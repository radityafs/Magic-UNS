package com.magic.officeapp.ui.screen.hr

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.data.model.response.request.GetAllRequestsDataItem
import com.magic.officeapp.data.model.response.request.GetRequestByIdData
import com.magic.officeapp.data.model.response.request.GetRequestByIdResponse
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.component.TextInput
import com.magic.officeapp.ui.navigation.Screen
import com.magic.officeapp.ui.screen.LoadingScreen
import com.magic.officeapp.ui.viewmodel.RequestViewModel
import com.magic.officeapp.utils.constants.Result
import com.magic.officeapp.utils.constants.utcToFormat

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HrRequestDetailScreen(
    navController: NavController = rememberNavController(),
    id: Int,
    requestViewModel: RequestViewModel = hiltViewModel()
) {
    var (isWaiting, setIsWaiting) = remember {
        mutableStateOf(true)
    }

    var (feedback, setFeedback) = remember {
        mutableStateOf("")
    }

    var request = requestViewModel.requestDetail.collectAsState().value

    var data: GetRequestByIdData? = null
    var loading = requestViewModel.loading.collectAsState().value
    requestViewModel.getRequestDetail(id)
    when (request) {
        is Result.Success -> {
            data = request.data.data
            var waiting : Boolean = false
            if (data?.attributes?.isApproved.toString() == "waiting") {
                waiting = true
            }

            setIsWaiting(waiting)
        }

        is Result.Error -> {
            Log.d("TAG", "AnnouncementScreen: ${request.message}")
        }

        else -> {

        }
    }

    var update = requestViewModel.addRequestState.collectAsState().value
    when (update) {
        is Result.Success -> {
            if (navController.currentDestination?.route != Screen.HrRequestScreen.route) {
                navController.navigate(Screen.HrRequestScreen.route) {
                    popUpTo(Screen.HrRequestScreen.route) {
                        inclusive = true
                    }
                }
            }
        }

        is Result.Error -> {
            Log.d("TAG", "HrRequestDetailScreen: ${update.message}")
        }

        else -> {

        }
    }

    fun rejected() {
        requestViewModel.updateRequestStatus(
            id = id,
            isApproved = "rejected",
            feedback = feedback
        )
        navController.navigate(Screen.HrRequestScreen.route)
    }

    fun approved() {
        requestViewModel.updateRequestStatus(
            id = id,
            isApproved = "approved",
            feedback = feedback
        )
        navController.navigate(Screen.HrRequestScreen.route)
    }


    LazyColumn(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxSize(),
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
                    text = "Requests Details",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Request Type",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
            )
            Text(
                text = data?.attributes?.requestType.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color("#151515".toColorInt()),
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Request Date",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
            )
            Text(
                text = utcToFormat(data?.attributes?.createdAt, "dd MMMM yyyy"),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color("#151515".toColorInt()),
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Request Time",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
            )
            Text(
                text = utcToFormat(data?.attributes?.createdAt, "HH:mm a"),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color("#151515".toColorInt()),
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Request Description",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
            )
            Text(
                text = data?.attributes?.note.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color("#151515".toColorInt()),
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Request By",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
            )
            Text(
                text = data?.attributes?.user?.data?.attributes?.username.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color("#151515".toColorInt()),
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Permit Date",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
            )
            Text(
                text = data?.attributes?.requestDate.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color("#151515".toColorInt()),
            )

            if(isWaiting) {
                Spacer(modifier = Modifier.height(24.dp))
                TextInput(
                    onValueChange = {
                        setFeedback(it)
                    },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .height(150.dp)
                        .fillMaxWidth(),
                    label = "Feedback",
                    type = "text",
                    value = feedback,
                    placeholder = "Enter Feedback Request (Optional)",
                )

                Spacer(modifier = Modifier.height(24.dp))

                CustomButton(
                    onClick = {
                        approved()
                    },
                    text = "Approve",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    backgroundColor = Color("#2C803A".toColorInt()),
                    contentColor = Color("#ffffff".toColorInt())
                )

                Spacer(modifier = Modifier.height(16.dp))
                CustomButton(
                    onClick = {
                        rejected()
                    },
                    text = "Reject",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    backgroundColor = Color("#D3221E".toColorInt()),
                    contentColor = Color("#ffffff".toColorInt())
                )

                Spacer(modifier = Modifier.height(50.dp))
            } else {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Feedback",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color("#858D9D".toColorInt()),
                )
                Text(
                    text = data?.attributes?.feedback.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color("#151515".toColorInt()),
                )
            }
        }
    }
}
