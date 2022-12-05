
package pizza.xyz.math

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import pizza.xyz.math.dao.DatabaseFactory
import pizza.xyz.math.plugins.configureRouting
import pizza.xyz.math.plugins.configureTemplating
import pizza.xyz.math.services.ArticleCreateService
import pizza.xyz.math.services.ArticleDeleteService
import pizza.xyz.math.services.ArticleEditService

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(Koin) {
        slf4jLogger()
        modules(helloAppModule)
    }
    DatabaseFactory.init()
    configureTemplating()
    configureRouting()
}

val helloAppModule = module {
    single { ArticleCreateService() }
    single { ArticleEditService() }
    single { ArticleDeleteService() }
}
