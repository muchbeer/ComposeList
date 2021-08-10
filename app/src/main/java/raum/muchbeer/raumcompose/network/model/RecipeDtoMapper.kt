package raum.muchbeer.raumcompose.network.model

import raum.muchbeer.raumcompose.domain.model.Recipe
import raum.muchbeer.raumcompose.domain.util.DomainMapper

class RecipeDtoMapper : DomainMapper<RecipeDtO, Recipe> {
    override fun mapToDomainModel(model: RecipeDtO): Recipe {
        return Recipe(
            id = model.pk,
            title = model.title,
            publisher = model.publisher,
            featuredImage = model.featuredImage,
            rating = model.rating,
            sourceUrl = model.sourceUrl,
            description = model.description,
            cookingInstructions = model.cookingInstructions,
            ingredients = model.ingredients.orEmpty(),
            dateAdded = model.dateAdded,
            dateUpdated = model.dateUpdated,

        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDtO {
     return RecipeDtO(
         pk = domainModel.id,
         title = domainModel.title,
         publisher = domainModel.publisher,
         featuredImage = domainModel.featuredImage,
         rating = domainModel.rating,
         sourceUrl = domainModel.sourceUrl,
         description = domainModel.description,
         cookingInstructions = domainModel.cookingInstructions,
         ingredients = domainModel.ingredients,
         dateAdded = domainModel.dateAdded,
         dateUpdated = domainModel.dateUpdated
     )   }

    fun toDomainList(initial: List<RecipeDtO>): List<Recipe>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Recipe>): List<RecipeDtO>{
        return initial.map { mapFromDomainModel(it) }
    }
}