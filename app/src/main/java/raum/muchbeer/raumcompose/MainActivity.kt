package raum.muchbeer.raumcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import raum.muchbeer.raumcompose.ui.theme.ComposeListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

       AllTogether()
        }
    }
}

@Composable
fun ButtonLayout() {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        Button(onClick = {
            Toast.makeText(context, "Button New Design", Toast.LENGTH_LONG).show()
        },
            shape = RectangleShape,
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.LightGray,
            contentColor = Color.White
        ),
        border = BorderStroke(2.dp, color = Color.Red),

        ) {
            Text(text = "My Family", fontSize = 25.sp,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(8.dp))
        }

        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .align(CenterHorizontally)
                ) {
                Icon(
                    Icons.Filled.Refresh , contentDescription = "Refresh",
                      tint = Color.DarkGray,
                    modifier = Modifier.size(80.dp))
        }
    }

}

@Composable
fun BoxLayout() {
    val context = LocalContext.current

    Box(
        Modifier
            .background(color = Color.Magenta)
            .size(200.dp, 230.dp),
            contentAlignment = Alignment.Center

            ) {
        Box(modifier = Modifier
            .background(color = Color.LightGray)
            .size(160.dp, 120.dp)) {

            //Button We have TextButton, OutlinedButton and Button

            Button(onClick = {
            Toast.makeText(context, "Andela empower community", Toast.LENGTH_LONG).show()
            },
             enabled = true
                ,
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black
            ), modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
                    .border(4.dp, Color.Green)){

                    Text(text = "Andela", fontSize = 20.sp)
            }
        }
        Text(text = "Matumbo", color = Color.Black,
            fontSize= 15.sp,
             style = MaterialTheme.typography.h3,
            modifier = Modifier
                .size(80.dp, 30.dp)
                .background(color = Color.White)
                .align(Alignment.BottomEnd)


            )
    }

}

@Composable
fun AllTogether() {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(color = Color.Cyan)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
       //SpaceAround : the last and first share same space
    ) {
        Greeting(name = "Muchbeer")
        Greeting(name = "Gadiel")
        Greeting(name = "Gianna")
    }
}
@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
         fontSize = 32.sp,
               modifier = Modifier
                   .padding(10.dp)
                   .border(2.dp, color = androidx.compose.ui.graphics.Color.Cyan))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  //  BoxLayout()
    ButtonLayout()
}