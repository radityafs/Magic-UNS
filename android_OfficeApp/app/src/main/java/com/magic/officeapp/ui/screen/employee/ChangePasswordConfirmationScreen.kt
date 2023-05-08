package com.magic.officeapp.ui.screen.employee

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.magic.officeapp.R
import com.magic.officeapp.ui.component.CustomButton
import com.magic.officeapp.ui.navigation.Screen

@Composable
fun ChangePasswordConfirmationScreen(
    navController: NavController = rememberNavController(),
) {
    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.password_changed_animation),
            contentDescription = "Password Changed Animation",
            modifier = Modifier
                .fillMaxWidth()
                .height(174.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Password successfully updated",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Your password has been successfully updated. Please use your new password for future logins.",
            color = Color("#858D9D".toColorInt()),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomButton(
            onClick = {
                navController.navigate(Screen.ProfileScreen.route) {
                    popUpTo(Screen.ProfileScreen.route) {
                        inclusive = true
                    }
                }
            },
            text = "Return to profile",
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ChangePasswordConfirmationScreenPreview() {
    ChangePasswordConfirmationScreen()
}