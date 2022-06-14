package slickDBStressTest

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class slickDBStressTest extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://localhost:8084")
    .inferHtmlResources(AllowList(), DenyList())
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("PostmanRuntime/7.29.0")
  
  private val headers_0 = Map(
  		"Content-Type" -> "text/plain",
  		"Postman-Token" -> "3b616f0a-38be-4010-bd77-0b039f4d1174"
  )
  
  private val headers_1 = Map("Postman-Token" -> "4eacfbc6-08a4-4051-bd9c-ae475e91c7fe")


  private val scn = scenario("MongoDBStressTest")
    .exec(
      http("request_0")
        .post("/save")
        .headers(headers_0)
        .body(RawFileBody("mongoDBStressTest/mongodbstresstest/0000_request.txt"))
        .basicAuth("cah","cah")
    )
    .pause(3)
    .exec(
      http("request_1")
        .get("/load")
        .headers(headers_1)
    )

	setUp(scn.inject(atOnceUsers(1000))).protocols(httpProtocol)
}
