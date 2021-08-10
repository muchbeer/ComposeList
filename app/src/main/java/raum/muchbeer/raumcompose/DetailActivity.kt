package raum.muchbeer.raumcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import raum.muchbeer.raumcompose.model.TvShow
import raum.muchbeer.raumcompose.ui.theme.ComposeListTheme

class DetailActivity : ComponentActivity() {

    companion object {
        private const val tvShowConst = "muchbeer.raum"
        fun intent(context: Context, tvShow: TvShow) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(tvShowConst, tvShow)
            }

    }

    private val retrieveTvShow : TvShow by lazy {
        intent?.getSerializableExtra(tvShowConst) as TvShow
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvDetailPage(tvShow = retrieveTvShow)
        }
    }
}

@Composable
fun TvDetailPage(tvShow: TvShow) {
   val scrollstate = rememberScrollState()
    Card(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        elevation = 10.dp
        ) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(scrollstate)
        .padding(10.dp)) {
                Image(painter = painterResource(id = tvShow.imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit
                  )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = " The name is : ${tvShow.name}", style = MaterialTheme.typography.h3)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = tvShow.overview, style = MaterialTheme.typography.h3)

    }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeListTheme {

    }
}