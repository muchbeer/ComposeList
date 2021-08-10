package raum.muchbeer.raumcompose.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.constraintlayout.widget.ConstraintLayout

@Composable
fun Menubar(executeMethod : ()-> Unit) {
    IconButton(onClick = { executeMethod() }) {
        Icon(Icons.Filled.MoreVert, contentDescription = "" )
    }
}