package pizza.xyz.math.services

import org.koin.core.module.KoinDefinition
import pizza.xyz.math.dao.dao
import java.beans.BeanProperty

class ArticleDeleteService {

    suspend fun deleteArticle(id: Int) = dao.deleteArticle(id)
}