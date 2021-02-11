package justinebateman.github.io.kotlinapitest.service

import io.restassured.RestAssured
import io.restassured.response.Response
import justinebateman.github.io.kotlinapitest.domain.Post
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Repository

@Repository
class PostsRepository(@Value("\${web.api.endpoint}") webApiEndpoint: String?) : BaseRepository(webApiEndpoint)
{
    /**
     * @return Post[]
     */
    fun getAllPosts(): Response {
        return RestAssured.given()
            .spec(requestSpecification)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .get("/posts")
    }

    /**
     * @return Post
     */
    fun addNewPost(post: Post): Response {
        return RestAssured.given()
            .spec(requestSpecification)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .request().body(post)
            .post("/posts")
    }
}