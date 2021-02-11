package justinebateman.github.io.kotlinapitest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class KotlinApiTestApplication

fun main(args: Array<String>) {
    runApplication<KotlinApiTestApplication>(*args)
}