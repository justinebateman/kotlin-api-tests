package justinebateman.github.io.kotlinapitest.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Post(var id: Int? = null, var userId: Int? = null, var title: String? = null, var body: String? = null)
{
}