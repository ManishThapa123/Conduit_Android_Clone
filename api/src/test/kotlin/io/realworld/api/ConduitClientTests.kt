package io.realworld.api

import io.realworld.api.models.entities.UserCreds
import io.realworld.api.models.requests.SignupRequest
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.random.Random

class ConduitClientTests {

    private val conduitClient = ConduitClient()

    /**
     * This function retrieves all the articles
     */
    @Test
     fun `GET articles`(){
        runBlocking {
            val articles = conduitClient.publicApi.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }

    /**
     * This function retrieves all the articles filtered using the query parameter author.
     */
    @Test
    fun `GET articles by author`(){
        runBlocking {
            val articles = conduitClient.publicApi.getArticles(author = "444")
            assertNotNull(articles.body()?.articles)
        }
    }
    /**
     * This function retrieves all the articles filtered using the query parameter tags.
     */
    @Test
    fun `GET articles by tags`(){
        runBlocking {
            val articles = conduitClient.publicApi.getArticles(tag = "butts")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `POST users - create user`(){
        val userCreds = UserCreds(
                "testemail${Random.nextInt(99,999)}@test.com",
                password ="pass${Random.nextInt(9999,999999)}",
                username = "random_user${Random.nextInt(99,999)}"
        )
        runBlocking {

            val resp = conduitClient.publicApi.signupUser(SignupRequest(userCreds))
            assertEquals(userCreds.username, resp.body()?.user?.username)

        }
    }
}