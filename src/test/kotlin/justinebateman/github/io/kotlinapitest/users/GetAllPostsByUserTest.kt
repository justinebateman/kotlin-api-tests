package justinebateman.github.io.kotlinapitest.users

import justinebateman.github.io.kotlinapitest.BaseTest
import justinebateman.github.io.kotlinapitest.domain.Post
import justinebateman.github.io.kotlinapitest.service.PostsRepository
import justinebateman.github.io.kotlinapitest.service.UsersRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.testng.annotations.Test

class GetAllPostsByUserTest : BaseTest()
{
    @Autowired
    private lateinit var usersRepository: UsersRepository

    @Test(groups = ["smoke"])
    fun getAllPostsByValidUser()
    {
        val userId = 1
        // call the get posts by user endpoint
        val posts: Array<Post> = usersRepository.getAllPostsByUser(userId)
            .then()
            .assertThat()
            .statusCode(200) // assert that status is 200
            .and()
            .extract()
            .body()
            .`as`(Array<Post>::class.java) // return the body as a list of posts

        // assert that posts were returned in the body
        assertThat(posts)
            .`as`("Check posts are returned")
            .isNotNull
        logger.info("Posts have been successfully returned")

        // assert that the content of the body is correct
        assertThat(posts[0].id)
            .`as`("Check post id is returned")
            .isGreaterThan(0)
        assertThat(posts[0].userId)
            .`as`("Check post user id is returned")
            .isGreaterThan(0)
        assertThat(posts[0].title)
            .`as`("Check post title is returned")
            .isNotEmpty
        assertThat(posts[0].body)
            .`as`("Check post body is returned")
            .isNotEmpty
        logger.info("Post content is correct")

        // assert that all posts returned are only from the user we asked for
        assertThat(posts)
            .`as`("Check that all posts returned are only from the user we asked for")
            .extracting("userId")
            .containsOnly(userId)
        logger.info("All posts returned are from the requested user")
    }

    @Test
    fun getAllPostsNonExistentUser()
    {
        val userId = 0

        // call the get posts by user endpoint
        val posts: Array<Post> = usersRepository.getAllPostsByUser(userId)
            .then()
            .assertThat()
            .statusCode(200) // assert that status is 200
            .and()
            .extract()
            .body()
            .`as`(Array<Post>::class.java) // return the body as a list of posts

        assertThat(posts)
            .`as`("Check that the list of posts returned is empty")
            .isEmpty()
        logger.info("Empty list of posts returned")
    }
}