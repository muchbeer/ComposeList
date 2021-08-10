package raum.muchbeer.raumcompose.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.TextFieldValue
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import raum.muchbeer.raumcompose.DetailActivity
import raum.muchbeer.raumcompose.compose.TvListItme
import raum.muchbeer.raumcompose.model.TvShow
import raum.muchbeer.raumcompose.presentation.search_component.SearchView
import raum.muchbeer.raumcompose.presentation.search_component.topBar
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(
                    topBar = { topBar()},
                    backgroundColor = MaterialTheme.colors.primaryVariant
                ) {
                Navigation()
                }
            }
        }
    }

    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainScreen(navController = navController)
            }
        /*    composable(
                "details/{tvShowName}",
                arguments = listOf(navArgument("tvShowName") { type = NavType.StringType })
            ) { backStackEntry ->
                backStackEntry.arguments?.getString("countryName")?.let { countryName ->
                    DetailsScreen(countryName = countryName)
                }
            }*/
        }
    }

    @Composable
    fun MainScreen(navController: NavController) {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        Column {
            SearchView(textState)
            TvShowList(navController = navController, state = textState)
        }
    }

    @Composable
    fun TvShowList(navController: NavController, state: MutableState<TextFieldValue>) {
        val tvShows = remember {  raum.muchbeer.raumcompose.data.TvShowList.tvShows   }

        var filteredTvShows: List<TvShow>
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            val searchedText = state.value.text
            filteredTvShows = if (searchedText.isEmpty()) {
                tvShows
            } else {
                val resultList = ArrayList<TvShow>()
                for (tvItem in tvShows) {
                    if (tvItem.name.lowercase(Locale.getDefault())
                            .contains(searchedText.lowercase(Locale.getDefault()))
                    ) {
                        resultList.add(tvItem)
                    }
                }
                resultList
            }

            items(
                items = filteredTvShows,
                itemContent = { filteredShowItem->
                    TvListItme(tvShow = filteredShowItem, clickedTvShow = {
                        //Open a Detail Activity
                        Toast.makeText(requireContext(), "selected is : ${it.name}", Toast.LENGTH_LONG).show()
                        startActivity(DetailActivity.intent(requireContext(), it))

                        Log.d("MAINACTIVITY", "JHU")
                        //Using Navigation
      /*                  navController.currentBackStackEntry?.arguments =
                            Bundle().apply {
                                putParcelable("tvShow", it)
                            }

                        navController.navigate("details/${it.name}") {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo("main") {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }*/
                    } )
                }
            )

        }
    }
}




