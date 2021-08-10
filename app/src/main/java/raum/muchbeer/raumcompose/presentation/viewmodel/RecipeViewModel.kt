package raum.muchbeer.raumcompose.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import raum.muchbeer.raumcompose.domain.model.Recipe
import raum.muchbeer.raumcompose.presentation.food_list.FoodCategory
import raum.muchbeer.raumcompose.presentation.food_list.getFoodCategory
import raum.muchbeer.raumcompose.repository.RecipeRepository
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String,
) : ViewModel(){

    val recipes : MutableState<List<Recipe>> = mutableStateOf(listOf())

    var query = mutableStateOf("")
    val loading = mutableStateOf(false)

    var scrollChipState : Float = 0f

    val selectedCategory : MutableState<FoodCategory?> = mutableStateOf(null)
    init {
        newSearch()
    }
    fun newSearch(){
        viewModelScope.launch {
            loading.value = true
           // resetSelectState()
            val result = repository.search(
                token = token,
                page = 1,
                query = query.value
            )
            recipes.value = result
            loading.value = false
        }
    }

    fun onQueryChange(receiveQuery: String) {
        this.query.value = receiveQuery
    }

    private fun resetSelectState() {
        recipes.value = listOf()
        if(selectedCategory.value?.value != query.value)
            clearSelectedCategory()
    }

    private fun clearSelectedCategory() {
       selectedCategory.value = null
    }
    fun onSelectedCategory(selected: String) {
        val newQuery = getFoodCategory(selected)
        selectedCategory.value = newQuery
        onQueryChange(selected)
    }

    fun onSelectScrollState(chipScrolState : Float) = viewModelScope.launch{
        scrollChipState = chipScrolState
    }
}