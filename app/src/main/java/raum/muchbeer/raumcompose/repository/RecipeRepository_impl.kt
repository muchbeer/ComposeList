package raum.muchbeer.raumcompose.repository

import raum.muchbeer.raumcompose.domain.model.Recipe
import raum.muchbeer.raumcompose.network.RecipeService
import raum.muchbeer.raumcompose.network.model.RecipeDtoMapper
import javax.inject.Inject

class RecipeRepository_impl @Inject constructor(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper,
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return mapper.toDomainList(recipeService.search(token = token, page = page, query = query).recipes)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token = token, id))
    }
}