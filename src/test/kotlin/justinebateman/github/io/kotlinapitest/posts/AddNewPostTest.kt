package justinebateman.github.io.kotlinapitest.posts

import justinebateman.github.io.kotlinapitest.BaseTest
import justinebateman.github.io.kotlinapitest.domain.Post
import justinebateman.github.io.kotlinapitest.service.PostsRepository
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.testng.annotations.Test

class AddNewPostTest : BaseTest()
{
    @Autowired
    private lateinit var postsRepository: PostsRepository

    @Test(groups = ["smoke"])
    fun addValidNewPost()
    {
        val postToAdd = Post(userId = 2,  title = "This is a new post", body = "This is the body of the post")

        // call the add new post endpoint
        val addedPost: Post = postsRepository.addNewPost(postToAdd)
            .then()
            .assertThat()
            .statusCode(201) // assert that status is 201
            .and()
            .extract()
            .body()
            .`as`(Post::class.java) // return the body as a post

        // assert that posts were returned in the body
        assertThat(addedPost)
            .`as`("Check the new post was returned")
            .isNotNull
        logger.info("Post was successfully returned")

        // assert that the content of the body is correct
        assertThat(addedPost.id)
            .`as`("Check post id is returned")
            .isGreaterThan(0)
        assertThat(addedPost.userId)
            .`as`("Check post user id matches what we added")
            .isEqualTo(postToAdd.userId)
        assertThat(addedPost.title)
            .`as`("Check post title matches what we added")
            .isEqualTo(postToAdd.title)
        assertThat(addedPost.body)
            .`as`("Check post body matches what we added")
            .isEqualTo(postToAdd.body)
        logger.info("Post content is correct")

    }

    // failing test - this should validate user id as a required field
    @Test(groups = ["known-issues"])
    fun addNewPostEmptyUserId()
    {
        val postToAdd = Post(userId = null,  title = "This is a new post", body = "This is the body of the post")

        // call the add new post endpoint
        postsRepository.addNewPost(postToAdd)
            .then()
            .assertThat()
            .statusCode(400) // assert that status is 400
    }

    // failing test - this should validate title as a required field
    @Test(groups = ["known-issues"])
    fun addNewPostEmptyTitle()
    {
        val postToAdd = Post(userId = 2,  title = "", body = "This is the body of the post")

        // call the add new post endpoint
        postsRepository.addNewPost(postToAdd)
            .then()
            .assertThat()
            .statusCode(400) // assert that status is 400
    }

    // failing test - this should validate body as a required field
    @Test(groups = ["known-issues"])
    fun addNewPostEmptyBody()
    {
        val postToAdd = Post(userId = 2,  title = "This is a new post", body = "")

        // call the add new post endpoint
        postsRepository.addNewPost(postToAdd)
            .then()
            .assertThat()
            .statusCode(400) // assert that status is 400
    }
}