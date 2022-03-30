
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CaHSpec extends AnyWordSpec with Matchers  {
  "The CaH main class" should {
    "accept text input as argument without readline loop, to test it from command line " in {
      CaHMain.main(Array[String]("q"))
    }
  }

}
