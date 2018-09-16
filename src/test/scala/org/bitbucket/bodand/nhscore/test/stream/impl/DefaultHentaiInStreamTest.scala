package org.bitbucket.bodand.nhscore.test.stream.impl

import org.bitbucket.bodand.nhscore.stream.impl.DefaultHentaiInStream
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DefaultHentaiInStreamTest extends WordSpec {
  private var inStream: DefaultHentaiInStream = _
  "A DefaultHentaiInStream" should {
    "initialize" in {
      inStream = new DefaultHentaiInStream
    }
  }
}
