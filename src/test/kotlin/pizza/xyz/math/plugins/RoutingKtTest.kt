package pizza.xyz.math.plugins;

import pizza.xyz.math.module

import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class RoutingKtTest {

    @Test
    fun testGetPerson() = testApplication {
        application {
            module()
        }
        client.get("/person").apply {
            TODO("Please write your test here")
        }
    }
}