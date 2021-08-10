package raum.muchbeer.raumcompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import raum.muchbeer.raumcompose.network.RecipeService
import raum.muchbeer.raumcompose.network.model.RecipeDtoMapper
import raum.muchbeer.raumcompose.repository.RecipeRepository
import raum.muchbeer.raumcompose.repository.RecipeRepository_impl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeMapper: RecipeDtoMapper,
    ): RecipeRepository {
        return RecipeRepository_impl(
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}