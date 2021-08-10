package raum.muchbeer.raumcompose.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import raum.muchbeer.raumcompose.model.TvShow


@Composable
 fun TvListItme(tvShow: TvShow, clickedTvShow: (TvShow) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable {
                    clickedTvShow(tvShow)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            TvImage(tvShow = tvShow)
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = tvShow.name, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = tvShow.overview,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = tvShow.year.toString(), style = MaterialTheme.typography.h5)
                    Text(text = tvShow.rating.toString(), style = MaterialTheme.typography.h5)
                }
            }
        }
    }
}
