package justinebateman.github.io.kotlinapitest.posts

import justinebateman.github.io.kotlinapitest.BaseTest
import justinebateman.github.io.kotlinapitest.domain.Post
import justinebateman.github.io.kotlinapitest.service.PostsRepository
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.testng.annotations.Test

class GetAllPostsTest : BaseTest()
{
    @Autowired
    private lateinit var postsRepository: PostsRepository

    @Test(groups = ["smoke"])
    fun getAllPosts()
    {
        // call the get all posts endpoint
        val posts: Array<Post> = postsRepository.getAllPosts()
            .then()
            .assertThat()
            .statusCode(200) // assert that status is 200
            .and()
            .extract()
            .body()
            .`as`(Array<Post>::class.java) // return the body as a list of posts

        // assert that posts were returned in the body
        Assertions.assertThat(posts)
            .`as`("Check posts are returned")
            .isNotNull
        logger.info("Posts have been successfully returned")

        // assert that the content of the body is correct
        Assertions.assertThat(posts[0].id)
            .`as`("Check post id is returned")
            .isGreaterThan(0)
        Assertions.assertThat(posts[0].userId)
            .`as`("Check post user id is returned")
            .isGreaterThan(0)
        Assertions.assertThat(posts[0].title)
            .`as`("Check post title is returned")
            .isNotEmpty
        Assertions.assertThat(posts[0].body)
            .`as`("Check post body is returned")
            .isNotEmpty
        logger.info("Post content is correct")

    }
}