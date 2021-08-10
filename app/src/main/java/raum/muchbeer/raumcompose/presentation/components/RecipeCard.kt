package raum.muchbeer.raumcompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import raum.muchbeer.raumcompose.R
import raum.muchbeer.raumcompose.domain.model.Recipe

@Composable
fun RecipeCard(
    recipe : Recipe,
    onClick : ()-> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 8.dp,
                top = 8.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Column() {
            recipe.featuredImage?.let {  imageUrl->
                val image = loadPicture(imageUrl = imageUrl, imageDefault = DEFAULT_RECIPE_IMAGE).value
                //This is to check if featuredImage is not null

                image?.let { img ->
                    Image(bitmap = img.asImageBitmap(), contentDescription=""  ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(225.dp),
                        contentScale = ContentScale.Crop)
                }

            }
            recipe.title?.let { title ->
                Row(
                    modifier = Modifier
                        .padding(
                            top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)

                        .fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = title, modifier = Modifier.padding(start = 8.dp)
                            .fillMaxWidth(0.85f),
                        style = MaterialTheme.typography.h5,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,

                    )

                    Text(text = recipe.rating.toString(), modifier = Modifier.padding(end = 8.dp),
                                        style = MaterialTheme.typography.h6)
                }

            }
        }
    }
}

