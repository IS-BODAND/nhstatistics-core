package tk.iscorp.nhs.test.stream.impl

import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.stream.impl.DefaultHentaiInStream

@RunWith(classOf[JUnitRunner])
class DefaultHentaiInStreamTest extends WordSpec {
  private var inStream: DefaultHentaiInStream = _
  "A DefaultHentaiInStream" should {
    "initialize" in {
      inStream = new DefaultHentaiInStream
    }
  }
}
