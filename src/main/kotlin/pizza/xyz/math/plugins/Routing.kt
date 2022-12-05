package pizza.xyz.math.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject
import pizza.xyz.math.dao.dao
import pizza.xyz.math.services.ArticleCreateService
import pizza.xyz.math.services.ArticleDeleteService
import pizza.xyz.math.services.ArticleEditService

fun Application.configureRouting() {

    val articleCreateService by inject<ArticleCreateService>()
    val articleEditService by inject<ArticleEditService>()
    val articleDeleteService by inject<ArticleDeleteService>()

    routing {
        // ...
        get("/") {
            call.respondRedirect("articles")
        }
        route("articles") {
            get {
                call.respond(FreeMarkerContent(
                    template = "index.ftl",
                    model = mapOf("articles" to dao.getAllArticles())
                ))
            }
            get("new") {
                call.respond(FreeMarkerContent(
                    template = "new.ftl",
                    model = null
                ))
            }
            post {
                articleCreateService.create(call.receiveParameters())
                call.respondRedirect("/articles/${articleCreateService.getId()}")
            }
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent(
                    template = "article.ftl",
                    model = mapOf("article" to dao.getArticle(id))
                ))
            }
            get("{id}/edit") {
                val id = call.parameters.getOrFail("id").toInt()
                call.respond(FreeMarkerContent(
                    template = "edit.ftl",
                    model = mapOf("article" to dao.getArticle(id))
                ))
            }
            post("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        articleEditService.editArticle(id, formParameters)
                        call.respondRedirect("/articles/$id")
                    }
                    "delete" -> {
                        articleDeleteService.deleteArticle(id)
                        call.respondRedirect("/articles")
                    }
                }
            }
        }
    }
}
