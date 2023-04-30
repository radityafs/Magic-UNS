package com.magic.officeapp.ui.screen

import android.widget.Space
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.CustomButton

@Composable
fun AnnouncementScreen(
    navController: NavController = rememberNavController(),
) {

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

        items(4, key = { index -> index.toString() }) {
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
                        text = "Request Activity",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color("#858D9D".toColorInt())
                    )

                    Text(
                        text = "11 mnt",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color("#858D9D".toColorInt())
                    )
                }

                Row(
                    modifier = Modifier.padding(top = 13.dp, bottom = 4.dp)
                ) {
                    Text(
                        text = "Request Accepted!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "The permit request on April 18, 2023 was approved.",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color("#858D9D".toColorInt())
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnnouncementScreen() {
    AnnouncementScreen()
}