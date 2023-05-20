package com.magic.officeapp.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.magic.officeapp.R
import com.magic.officeapp.ui.theme.Green500
import com.magic.officeapp.ui.theme.Red500
import com.magic.officeapp.utils.constants.utcToFormat
import java.text.NumberFormat
import java.util.*

fun numberToCurrency(number: String): String {
    val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    format.currency = java.util.Currency.getInstance("IDR")
    return format.format(number.toInt())
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardPayroll(
    salaryGross: String,
    salaryNet: String,
    created_at: String,
    salaryDate: String,
    onClick: () -> Unit
) {
    val salaryGrossInt = salaryGross.toInt()
    val salaryNetInt = salaryNet.toInt()

    var icon = if (salaryNetInt > salaryGrossInt) {
        R.drawable.state_market_up
    } else {
        R.drawable.state_market_down
    }

    var textColor = if (salaryNetInt > salaryGrossInt) {
        Green500
    } else {
        Red500
    }

    var monthSalary = utcToFormat(salaryDate, "MMMM yyyy")
    var generatedDate = utcToFormat(created_at, "dd MMMM yyyy HH:mm a")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(91.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.width(220.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(painter = painterResource(id = icon), modifier = Modifier.size(48.dp), contentDescription = "animate")

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = monthSalary,
                    color = Color("#292D35".toColorInt()),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp),
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = generatedDate, color = Color("#667085".toColorInt()), fontSize = 12.sp
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
        ) {

            Text(
                text = numberToCurrency(salaryNet),
                color = Color("#292D35".toColorInt()),
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 2.dp),
                fontWeight = FontWeight.W600
            )

            Text(
                text = numberToCurrency((salaryNetInt - salaryGrossInt).toString()),
                color = textColor,
                fontSize = 12.sp,
            )
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CardPayrollPreview() {
    CardPayroll(
        salaryGross = "5500000",
        salaryNet = "5750000",
        created_at = "2021-08-01T00:00:00.000Z",
        salaryDate = "2021-08-01T00:00:00.000Z",
        onClick = {}
    )
}
