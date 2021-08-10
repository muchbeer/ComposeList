package raum.muchbeer.raumcompose.network.model

import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(

@SerializedName("count")
var count: Int,

@SerializedName("results")
var recipes: List<RecipeDtO>,

)
