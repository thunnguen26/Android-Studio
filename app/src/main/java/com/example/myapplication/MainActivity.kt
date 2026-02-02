package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    val backgroundColor = Color(0xFFD2E8D4)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        MainInfoSection()

        Spacer(modifier = Modifier.weight(1f))

        ContactInfoSection()

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true, name = "Danh Thiep Preview")
@Composable
fun BusinessCardPreview() {
    MyApplicationTheme {
        BusinessCardApp()
    }
}

@Composable
fun MainInfoSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFF073042))
                .padding(16.dp)
        )

        Text(
            text = "Jennifer Doe",
            fontSize = 48.sp,
            color = Color(0xFF1B1B1B)
        )

        Text(
            text = "Android Developer Extraordinaire",
            fontSize = 18.sp,
            color = Color(0xFF3DDC84)
        )
    }
}

@Composable
fun ContactInfoSection(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val iconTint = Color(0xFF006D3B)
        val textColor = Color(0xFF1B1B1B)

        ContactRow(icon = Icons.Default.Call, text = "+11 (123) 444 555 666", iconTint = iconTint, textColor = textColor)
        ContactRow(icon = Icons.Default.Share, text = "@AndroidDev", iconTint = iconTint, textColor = textColor)
        ContactRow(icon = Icons.Default.Email, text = "jen.doe@android.com", iconTint = iconTint, textColor = textColor)
    }
}

@Composable
fun ContactRow(
    icon: ImageVector,
    text: String,
    iconTint: Color,
    textColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = textColor
        )
    }
}