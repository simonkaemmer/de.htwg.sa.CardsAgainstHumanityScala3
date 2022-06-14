package mongoDBSpikeTest

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class mongoDBSpikeTest extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://localhost:8084")
    .inferHtmlResources(AllowList(), DenyList())
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("PostmanRuntime/7.29.0")
  
  private val headers_0 = Map(
  		"Content-Type" -> "text/plain",
  		"Postman-Token" -> "7c602656-47c1-4762-bb87-4b979d06a27a"
  )
  
  private val headers_1 = Map("Postman-Token" -> "d2f508ff-941b-441f-87c0-808fb5569838")


  private val scn = scenario("mongoDBSpikeTest")
    .exec(
      http("request_0")
        .post("/save")
        .headers(headers_0)
        .body(RawFileBody("mongoDBSpikeTest/mongodbspiketest/0000_request.txt"))
        .basicAuth("cah","cah")
    )
    .exec(
      http("request_1")
        .get("/load")
        .headers(headers_1)
    )

	setUp(scn.inject(atOnceUsers(20000))).protocols(httpProtocol)
}
