package com.example.androidfloatingactionbutton

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainFun()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MainFun() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(20.dp)
    ) {
        val mContext = LocalContext.current
        val textInput = rememberSaveable() { mutableStateOf("") }
        val arr = rememberSaveable { mutableStateListOf<String>() }
        Text(
            "Заметки",
            fontSize = 32.sp,
            textAlign = Center,
            color = Color.White,
            modifier = Modifier
                .padding(5.dp)
                .background(Color.DarkGray)
                .fillMaxWidth()
        )
        TextField(
            value = textInput.value,
            textStyle = TextStyle(fontSize = 24.sp),
            onValueChange = {
                textInput.value = it
            },
            modifier = Modifier
                .border(2.dp, Color.Black)
                .fillMaxWidth()
        )
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            items(arr) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp)
                        .background(Color.White, RoundedCornerShape(50.dp))
                ) {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(start = 20.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = {
                                arr.remove(it)
                            }
                        ) {
                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(40.dp)
                            )
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = {
                if (textInput.value != "") {
                    arr.add(textInput.value)
                    textInput.value = ""
                } else {
                    Toast.makeText(
                        mContext,
                        "Поле ввода не может быть пустым",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}