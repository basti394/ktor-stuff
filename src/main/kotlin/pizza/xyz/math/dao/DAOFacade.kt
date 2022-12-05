package pizza.xyz.math.dao

import pizza.xyz.math.models.Article
import pizza.xyz.math.models.Articles

interface DAOFacade {
    suspend fun getAllArticles(): List<Article>
    suspend fun getArticle(id: Int): Article?
    suspend fun addNewArticle(title: String, body: String): Article?
    suspend fun editArticle(id: Int, title: String, body: String): Boolean
    suspend fun deleteArticle(id: Int): Boolean
}