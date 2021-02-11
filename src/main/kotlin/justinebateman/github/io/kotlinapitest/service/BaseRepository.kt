package justinebateman.github.io.kotlinapitest.service

import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.DecoderConfig
import io.restassured.config.EncoderConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.specification.RequestSpecification
import org.springframework.beans.factory.annotation.Value

abstract class BaseRepository(@Value("\${web.api.endpoint}") webApiEndpoint: String?)
{
    protected var requestSpecification: RequestSpecification? = null

    private var isRestAssuredConfigured = false
    private val enableLogging = true

   init
    {
        if (!isRestAssuredConfigured)
        {
            val requestSpecBuilder = RequestSpecBuilder()
            val restAssuredConfig = RestAssuredConfig()
                .encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8").defaultQueryParameterCharset("UTF-8"))
                .decoderConfig(DecoderConfig.decoderConfig().defaultCharsetForContentType("UTF-8", "application/json"))
            requestSpecification = requestSpecBuilder
                .setBaseUri(webApiEndpoint)
                .setConfig(restAssuredConfig)
                .setRelaxedHTTPSValidation()
                .build()
            if (enableLogging)
            {
                requestSpecification = requestSpecBuilder
                    .addFilter(RequestLoggingFilter())
                    .addFilter(ResponseLoggingFilter())
                    .build()
            }
        }
        isRestAssuredConfigured = true
    }
}