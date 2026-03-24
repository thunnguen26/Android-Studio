package com.example.myapplication.lab7

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.lab7.ui.theme.FirebaseprojectTheme
import com.example.myapplication.lab7.ui.theme.greenColor
import com.google.firebase.firestore.FirebaseFirestore

class UpdateCourse : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseprojectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            CenterAlignedTopAppBar(
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = greenColor,
                                    titleContentColor = Color.White
                                ),
                                title = {
                                    Text(
                                        text = "GFG - Update Course",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) { innerPadding ->
                        FirebaseUI(
                            context = LocalContext.current,
                            name = intent.getStringExtra("courseName"),
                            duration = intent.getStringExtra("courseDuration"),
                            description = intent.getStringExtra("courseDescription"),
                            courseID = intent.getStringExtra("courseID"),
                            modifier = Modifier.padding(innerPadding),
                            onUpdateSuccess = {
                                finish() // Đóng Activity sau khi update thành công
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun FirebaseUI(
        context: Context,
        name: String?,
        duration: String?,
        description: String?,
        courseID: String?,
        modifier: Modifier = Modifier,
        onUpdateSuccess: () -> Unit
    ) {
        val courseName = remember { mutableStateOf(name ?: "") }
        val courseDuration = remember { mutableStateOf(duration ?: "") }
        val courseDescription = remember { mutableStateOf(description ?: "") }

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = courseName.value,
                onValueChange = { courseName.value = it },
                placeholder = { Text(text = "Enter your course name") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = courseDuration.value,
                onValueChange = { courseDuration.value = it },
                placeholder = { Text(text = "Enter your course duration") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = courseDescription.value,
                onValueChange = { courseDescription.value = it },
                placeholder = { Text(text = "Enter your course description") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (TextUtils.isEmpty(courseName.value)) {
                        Toast.makeText(context, "Please enter course name", Toast.LENGTH_SHORT).show()
                    } else if (TextUtils.isEmpty(courseDuration.value)) {
                        Toast.makeText(context, "Please enter course Duration", Toast.LENGTH_SHORT).show()
                    } else if (TextUtils.isEmpty(courseDescription.value)) {
                        Toast.makeText(context, "Please enter course description", Toast.LENGTH_SHORT).show()
                    } else {
                        updateDataToFirebase(
                            courseID,
                            courseName.value,
                            courseDuration.value,
                            courseDescription.value,
                            context,
                            onUpdateSuccess
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Update Data", modifier = Modifier.padding(8.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    private fun updateDataToFirebase(
        courseID: String?,
        name: String?,
        duration: String?,
        description: String?,
        context: Context,
        onUpdateSuccess: () -> Unit
    ) {
        val updatedCourse = Course(courseID, name, duration, description)
        val db = FirebaseFirestore.getInstance()

        if (courseID != null) {
            db.collection("Courses").document(courseID).set(updatedCourse)
                .addOnSuccessListener {
                    Toast.makeText(context, "Course Updated successfully..", Toast.LENGTH_SHORT).show()
                    onUpdateSuccess()
                }.addOnFailureListener {
                    Toast.makeText(context, "Fail to update course : " + it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }
}