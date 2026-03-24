package com.example.myapplication.lab7

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.lab7.ui.theme.FirebaseprojectTheme
import com.example.myapplication.lab7.ui.theme.greenColor
import com.google.firebase.firestore.FirebaseFirestore

class CourseDetailsActivity : ComponentActivity() {

    private val courseList = mutableStateListOf<Course?>()

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
                                        text = "GFG - Course List",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) { innerPadding ->
                        FirebaseUI(
                            context = LocalContext.current,
                            courseList = courseList,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadCourses()
    }

    private fun loadCourses() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Courses").get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    courseList.clear()
                    for (d in queryDocumentSnapshots.documents) {
                        val c = d.toObject(Course::class.java)
                        if (c != null) {
                            c.courseID = d.id
                            courseList.add(c)
                        }
                    }
                } else {
                    Toast.makeText(this, "No data found in Database", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Fail to get the data.", Toast.LENGTH_SHORT).show()
            }
    }

    @Composable
    fun FirebaseUI(context: Context, courseList: SnapshotStateList<Course?>, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                itemsIndexed(courseList) { index, item ->
                    Card(
                        onClick = {
                            val i = Intent(context, UpdateCourse::class.java)
                            i.putExtra("courseName", item?.courseName)
                            i.putExtra("courseDuration", item?.courseDuration)
                            i.putExtra("courseDescription", item?.courseDescription)
                            i.putExtra("courseID", item?.courseID)
                            context.startActivity(i)
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = item?.courseName ?: "",
                                color = greenColor,
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Duration: ${item?.courseDuration}",
                                color = Color.Black,
                                style = TextStyle(fontSize = 15.sp)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = item?.courseDescription ?: "",
                                color = Color.Gray,
                                style = TextStyle(fontSize = 14.sp)
                            )
                        }
                    }
                }
            }
        }
    }
}