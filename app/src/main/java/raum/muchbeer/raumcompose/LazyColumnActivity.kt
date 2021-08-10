package raum.muchbeer.raumcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import raum.muchbeer.raumcompose.compose.TvListItme
import raum.muchbeer.raumcompose.data.TvShowList
import raum.muchbeer.raumcompose.model.TvShow

class LazyColumnActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           // LazyColumnDemo()
           DisplayListWithLazy {
               DetailActivity.intent(this, it)
               Toast.makeText(this, "selected is : ${it.name}", Toast.LENGTH_LONG).show()
           }
        }
    }

}

@Composable
private fun DisplayListWithLazy(selectedItem: (TvShow) -> Unit) {
    val tvShow = remember {  TvShowList.tvShows   }

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp,
    vertical = 8.dp)) {
        items(
            items = tvShow,
            itemContent = {
                TvListItme(tvShow = it,clickedTvShow = selectedItem)
            }
        )

    }
}


@Composable
fun LazyColumnDemo2(selectedItem : (String) -> Unit) {
    LazyColumn{
        items(100) {
            Text(text = "The list of ${it}",
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        selectedItem("${it} is Selected")
                    })


            Divider(color = Color.LightGray, thickness = 3.dp)
        }
    }
}

@Composable
fun LazyColumnDemo() {
    LazyColumn{
        items(100) {
            Text(text = "The list of ${it}",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(10.dp))

            Divider(color = Color.LightGray, thickness = 3.dp)
        }
    }
}



