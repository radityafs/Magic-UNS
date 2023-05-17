package com.magic.officeapp.ui.screen.employee

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.data.model.response.request.GetRequestByIdData
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.viewmodel.RequestViewModel
import com.magic.officeapp.utils.constants.Result
import com.magic.officeapp.utils.constants.utcToFormat

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RequestDetailScreen(
    navController: NavController = rememberNavController(),
    requestViewModel : RequestViewModel = hiltViewModel(),
    id: Int,
) {
    var request = requestViewModel.requestDetail.collectAsState().value

    var data: GetRequestByIdData? = null

    requestViewModel.getRequestDetail(id)
    when (request) {
        is Result.Success -> {
            data = request.data.data
        }

        is Result.Error -> {
            Log.d("TAG", "AnnouncementScreen: ${request.message}")
        }

        else -> {

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
                text = "Request Detail",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(data?.attributes?.isApproved == "approved") {
                Image(
                    painter = painterResource(R.drawable.state_success),
                    contentDescription = "Success",
                    modifier = Modifier.size(72.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
            }

            Text(
                text = if (data?.attributes?.isApproved.toString() == "approved") "Approved" else if (data?.attributes?.isApproved.toString() == "waiting") "Waiting" else "Rejected",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.height(40.dp))
        }

        Column {
            Text(
                text = "Request Type",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt())
            )
            Text(text = if(data?.attributes?.requestType.toString() == "permit") "Request Permit" else "Request Other", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(24.dp))
        }

        Column {
            Text(
                text = "Request date",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = utcToFormat(data?.attributes?.createdAt, "dd MMMM yyyy"), fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(24.dp))
        }
        Column {
            Text(
                text = "Request time",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color("#858D9D".toColorInt()),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = utcToFormat(data?.attributes?.createdAt, "HH:mm a"), fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(24.dp))
        }

        if(data?.attributes?.requestType.toString() == "permit") {
            Column {
                Text(
                    text = "Permit Date",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color("#858D9D".toColorInt()),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = data?.attributes?.requestDate.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

        } else {
            Column {
                Text(
                    text = "Description",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color("#858D9D".toColorInt()),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = data?.attributes?.note.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }


        if(data?.attributes?.isApproved != "waiting") {
            Column {
                Text(
                    text = "Feedback",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color("#858D9D".toColorInt()),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = data?.attributes?.feedback.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }



        Column(modifier = Modifier.fillMaxWidth()) {
            CustomButton(
                onClick = {
                    navController.popBackStack()
                },
                text = "Return to Request List",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            )
        }
    }
}