package pizza.xyz.math.services

import io.ktor.http.*
import io.ktor.server.util.*
import pizza.xyz.math.dao.dao
import pizza.xyz.math.models.Article

class ArticleCreateService {

    private lateinit var title: String
    private lateinit var body: String

    private var article: Article? = null


    suspend fun create(params: Parameters) {
        setProps(params)
        article = dao.addNewArticle(title, body)
        return
    }

    private fun setProps(params: Parameters) {
        title = params.getOrFail("title")
        body = params.getOrFail("body")
    }

    fun getId() = article?.id
}