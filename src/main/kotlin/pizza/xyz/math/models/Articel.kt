package pizza.xyz.math.models

import org.jetbrains.exposed.sql.Table
import java.util.concurrent.atomic.AtomicInteger

data class Article(
    val id: Int,
    var title: String,
    var body: String
)

object Articles : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 128)
    val body = varchar("body", 1024)

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}