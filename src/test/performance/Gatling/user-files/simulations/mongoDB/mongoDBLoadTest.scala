package mongoDBLoadTest

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class mongoDBLoadTest extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://localhost:8084")
    .inferHtmlResources(AllowList(), DenyList())
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("PostmanRuntime/7.29.0")
  
  private val headers_0 = Map(
  		"Content-Type" -> "text/plain",
  		"Postman-Token" -> "14abceb2-c3ba-4c48-af3a-bc82cb46c1dc"
  )
  
  private val headers_1 = Map("Postman-Token" -> "abd56510-15c6-4de5-8d82-0cdb59851462")


  private val scn = scenario("mongoDBLoadTest")
   .repeat(100, "i") {
     exec(
      http("request_0")
        .post("/save")
        .headers(headers_0)
        .body(RawFileBody("mongoDBLoadTest/mongodbloadtest/0000_request.txt"))
        .basicAuth("cah","cah")
    )
    .pause(2)
    .exec(
      http("request_1")
        .get("/load")
        .headers(headers_1)
    )
}

	setUp(scn.inject(atOnceUsers(5))).protocols(httpProtocol)
}
