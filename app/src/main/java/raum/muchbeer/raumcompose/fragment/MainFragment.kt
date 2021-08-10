package raum.muchbeer.raumcompose.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import raum.muchbeer.raumcompose.ProgressBarViewExt
import raum.muchbeer.raumcompose.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_main_page_fragment, container, false)

        val dottedView = view.findViewById<ComposeView>(R.id.composeView).setContent {
            Column(
                modifier = Modifier
                    .border(1.dp, Color.Green)
                    .padding(10.dp)
            ) {
                Text(text = "Welcome to composable view")
                Spacer(modifier = Modifier.padding(10.dp))
                CircularProgressIndicator()
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Giovanna")
                Spacer(modifier = Modifier.padding(10.dp))
                val customView = ProgressBarViewExt(LocalContext.current)
                AndroidView(factory = { customView })
            }
        }
        /*        ComposeView(requireContext()).apply {
    setContent {
        Text(text = "This is the beginning")
    }
}*/
        return view
    }
}
