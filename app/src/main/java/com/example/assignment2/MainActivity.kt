package com.example.assignment2

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment2.ui.theme.Assignment2Theme
import androidx.compose.foundation.lazy.itemsIndexed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ColorChangingButton()
                    LayoutsPractice("Abdul Saboor","0346 8266660", R.drawable.polar)
                    MultipleRows()
                    PhotoGrid()
                }
            }
        }
    }
}

// Task 1
@Composable
fun ColorChangingButton() {
    Column(Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var color by remember { mutableStateOf(Color.Red) }

        Button(
            onClick = {
                color = if (color == Color.Red) Color.Green else Color.Red
            },
            colors = ButtonDefaults.buttonColors(containerColor = color)
        )
        {
            Text(text = "Click Me")
        }
    }
}


// Task 2
@Composable
fun LayoutsPractice(Name: String, Number: String, imgResource: Int){
    Row (Modifier.fillMaxWidth()){
        Column {
        Image(painter = painterResource(id = imgResource), contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(120.dp, 120.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        }
        Column (modifier = Modifier
            .padding(10.dp)){
            Row {
                Text(Name, fontSize = 30.sp)
            }
            Row {
                Text(Number, fontSize = 20.sp, modifier = Modifier.padding(top = 10.dp))
            }
        }
    }
}

// Task 3
@Composable
fun MultipleRows() {
    Column (Modifier.fillMaxSize()) {
        LayoutsPractice(Name = "Iron Man", Number = "Age: 43", imgResource = R.drawable.ironman)
        LayoutsPractice(Name = "Hulk", Number = "Age: 38", imgResource = R.drawable.hulk)
        LayoutsPractice(Name = "Dead Pool", Number = "Age: 25", imgResource = R.drawable.deadpool)
        LayoutsPractice(Name = "Wolverine", Number = "Age: 48", imgResource = R.drawable.wol)
        LayoutsPractice(Name = "Black Widow", Number = "Age: 30", imgResource = R.drawable.blackwidow)
        LayoutsPractice(Name = "Thor", Number = "Age: 35", imgResource = R.drawable.thor)
    }
}

// Task 4
enum class Superhero(val resourceId: Int, val displayName: String) {
    IRON_MAN(R.drawable.ironman, "Iron Man"),
    HULK(R.drawable.hulk, "Hulk"),
    DEADPOOL(R.drawable.deadpool, "Deadpool"),
    WOLVERINE(R.drawable.wol, "Wolverine"),
    BLACK_WIDOW(R.drawable.blackwidow, "Black Widow"),
    THOR(R.drawable.thor, "Thor")
}

@Composable
fun PhotoItem(superhero: Superhero) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = superhero.resourceId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
        }
        Text(
            text = superhero.displayName,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PhotoGrid() {
    val superheroes = Superhero.values().toList()

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        itemsIndexed(superheroes) { index, superhero ->
            PhotoItem(superhero)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment2Theme {
        LayoutsPractice("Abdul Saboor","0346 8266660", R.drawable.polar)
        ColorChangingButton()
        MultipleRows()
        PhotoGrid()
    }
}
