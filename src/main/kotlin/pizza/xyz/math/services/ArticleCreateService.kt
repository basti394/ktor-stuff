package pizza.xyz.math.services

import io.ktor.http.*
import io.ktor.server.util.*
import pizza.xyz.math.models.Article
import pizza.xyz.math.models.articles

class ArticleCreateService {

    private lateinit var title: String
    private lateinit var body: String

    private lateinit var newEntry: Article

    fun create(params: Parameters) {
        setProps(params)
        newEntry = Article.newEntry(title, body)
        articles.add(newEntry)
        return
    }

    private fun setProps(params: Parameters) {
        title = params.getOrFail("title")
        body = params.getOrFail("body")
    }

    fun getId() = newEntry.id
}