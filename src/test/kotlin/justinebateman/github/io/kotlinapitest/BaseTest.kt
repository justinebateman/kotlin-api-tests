package justinebateman.github.io.kotlinapitest

import justinebateman.github.io.kotlinapitest.domain.Post
import justinebateman.github.io.kotlinapitest.service.PostsRepository
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.testng.annotations.Test

@SpringBootTest
open class BaseTest : AbstractTestNGSpringContextTests()
{



}