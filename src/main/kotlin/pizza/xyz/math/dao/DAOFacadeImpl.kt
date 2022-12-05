package pizza.xyz.math.dao

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import pizza.xyz.math.dao.DatabaseFactory.dbQuery
import pizza.xyz.math.models.Article
import pizza.xyz.math.models.Articles

class DAOFacadeImpl : DAOFacade {

    private fun resultRowToArticle(row: ResultRow) = Article(
        id = row[Articles.id],
        title = row[Articles.title],
        body = row[Articles.body],
    )
    override suspend fun getAllArticles(): List<Article> = dbQuery {
        Articles.selectAll().map(::resultRowToArticle)
    }

    override suspend fun getArticle(id: Int): Article? = dbQuery {
        Articles
            .select { Articles.id eq id }
            .map(::resultRowToArticle)
            .singleOrNull()
    }

    override suspend fun addNewArticle(title: String, body: String): Article? = dbQuery {
        val insertStatement = Articles.insert {
            it[Articles.title] = title
            it[Articles.body] = body
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

    override suspend fun editArticle(id: Int, title: String, body: String): Boolean = dbQuery {
        Articles.update({ Articles.id eq id }) {
            it[Articles.title] = title
            it[Articles.body] = body
        } > 0
    }

    override suspend fun deleteArticle(id: Int): Boolean = dbQuery {
        Articles.deleteWhere { Articles.id eq id } > 0
    }
}

val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if (getAllArticles().isEmpty()) {
            addNewArticle("The drive to develop!", "...it's what keeps me going.")
        }
    }
}