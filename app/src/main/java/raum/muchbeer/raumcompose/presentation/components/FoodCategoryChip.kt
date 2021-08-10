package raum.muchbeer.raumcompose.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun foodCategoryChip(
    foodCategory: String,
    isSelected : Boolean = false,
    onSelectedChange : (String)->Unit,
    onExecuteSearch: ()->Unit
) {
        Surface(
            modifier = Modifier.padding(end = 8.dp),
            shape = MaterialTheme.shapes.medium,
            color =  if(isSelected) Color.DarkGray else MaterialTheme.colors.primary,
            elevation = 8.dp
        ) {
            Row(modifier = Modifier.toggleable(value = isSelected, onValueChange = {
                onSelectedChange(foodCategory)
                onExecuteSearch()
            })) {
                Text(text = foodCategory, style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(8.dp), color = Color.White)
            }
        }
}