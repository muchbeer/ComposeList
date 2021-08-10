package raum.muchbeer.raumcompose.fragment

import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.IconCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import raum.muchbeer.raumcompose.presentation.components.CircularProgressInfinite
import raum.muchbeer.raumcompose.presentation.components.Menubar
import raum.muchbeer.raumcompose.presentation.viewmodel.RecipeViewModel
import raum.muchbeer.raumcompose.presentation.components.RecipeCard
import raum.muchbeer.raumcompose.presentation.components.foodCategoryChip
import raum.muchbeer.raumcompose.presentation.food_list.getAllFoodCategories

@AndroidEntryPoint
class NavHostFragmentMain : Fragment() {

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {


              /*  AppTheme or ComposeListThem(
                    darkTheme = application.isDark.value (the boolean here should be store in the datastore)
                ) {
                    //all the code should be inside here
                }*/
                val recipes = viewModel.recipes.value

                val localFocusManager = LocalFocusManager.current
                val scrollState = rememberScrollState()
                val loading = viewModel.loading.value

                recipes.forEach {
                    Log.d("MainFragment", "The value of recipe is ${it.title}")
                }

                val query = viewModel.query
                val selectedCategory = viewModel.selectedCategory.value
                Column() {
//Surface allow to do elevation
                    Surface(
                        elevation = 8.dp, color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row() {
                            TextField(
                                value = query.value,
                                modifier = Modifier
                                    .padding(start = 8.dp, bottom = 8.dp, top = 8.dp)
                                    .fillMaxWidth(0.9f),
                                label = {
                                    Text(text = "Search")
                                },
                                onValueChange = {
                                    viewModel.onQueryChange(it)
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Search
                                ),
                                leadingIcon = {
                                    Icon(Icons.Filled.Search, contentDescription = null)
                                },
                                keyboardActions = KeyboardActions(onSearch = {
                                    viewModel.newSearch()
                                    localFocusManager.clearFocus()
                                }),
                                textStyle = TextStyle(
                                    color = MaterialTheme.colors.onSurface,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                            )
                            Spacer(modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, top = 8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth()
                                .align(Alignment.CenterVertically),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Menubar {
                                    Toast.makeText(
                                        requireContext(),
                                        "Get theme here",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, bottom = 8.dp)
                                .horizontalScroll(scrollState)
                        ) {
                            // scrollState.scrollTo(viewModel.scrollChipState.toInt())
                            for (category in getAllFoodCategories()) {
                                foodCategoryChip(
                                    foodCategory = category.value,
                                    isSelected = selectedCategory == category,
                                    onSelectedChange = {
                                        viewModel.onSelectedCategory(it)
                                        //    viewModel.onSelectScrollState(scrollState.value.toFloat())
                                    }, onExecuteSearch = { viewModel.newSearch() }
                                )

                            }
                        }

                        Spacer(modifier = Modifier.padding(10.dp))
                        Box(modifier = Modifier.fillMaxSize()) {
                            LazyColumn {
                                itemsIndexed(items = recipes) { index, recipe ->
                                    RecipeCard(recipe = recipe, onClick = {})
                                }
                            }
                            CircularProgressInfinite(isInternet = loading)
                        }

                    }

                    /*             Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = "Using Navigation Componsent to Navigate",
                        modifier = Modifier.size(20.dp),
                          style = TextStyle(fontStyle = FontStyle.Italic)

                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(onClick = {
                        findNavController().navigate(R.id.action_navHostFragmentMain_to_navHostFragmentSub)
                    }) {
                        Text(text = "Submit")
                    }
                }*/

                    /*
                *   setContent {

                val recipes = viewModel.recipes.value

                val query = viewModel.query.value

                val selectedCategory = viewModel.selectedCategory.value

                val categoryScrollPosition = viewModel.categoryScrollPosition

                Column {

                    SearchAppBar(
                            query = query,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = viewModel::newSearch,
                            categories = getAllFoodCategories(),
                            selectedCategory = selectedCategory,
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            scrollPosition = categoryScrollPosition,
                            onChangeScrollPosition = viewModel::onChangeCategoryScrollPosition,
                    )

                    LazyColumn {
                        itemsIndexed(
                            items = recipes
                        ){index, recipe ->
                            RecipeCard(recipe = recipe, onClick = {})
                        }
                    }
                }
            }
        }*/

                }
            }
        }
    }
