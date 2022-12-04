package pizza.xyz.math.services

import io.ktor.http.*
import io.ktor.server.util.*
import pizza.xyz.math.models.articles

class ArticleEditService {

    fun editArticle(id: Int, params: Parameters) {
        val index = articles.indexOf(articles.find { it.id == id })
        val title = params.getOrFail("title")
        val body = params.getOrFail("body")
        articles[index].title = title
        articles[index].body = body
    }
}