package pizza.xyz.math.services

import io.ktor.http.*
import io.ktor.server.util.*
import pizza.xyz.math.dao.dao

class ArticleEditService {

    suspend fun editArticle(id: Int, params: Parameters) {
        val title = params.getOrFail("title")
        val body = params.getOrFail("body")

        dao.editArticle(id, title, body)
    }
}