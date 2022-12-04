package pizza.xyz.math.services

import org.koin.core.module.KoinDefinition
import pizza.xyz.math.models.articles
import java.beans.BeanProperty

class ArticleDeleteService {

    fun deleteArticle(id: Int) = articles.removeIf { it.id == id }
}