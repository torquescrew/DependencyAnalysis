package test.io.utils

import scala.collection.mutable.ArrayBuffer

/**
 * Created with IntelliJ IDEA.
 * User: tobysuggate
 * Date: 19/08/13
 * Time: 8:38 PM
 */
class Statement(line: String) {
  val operators = Array(':',';','!','<','>','{','}','*','(',')','&',',','-',' ','=')
  val mTokens = parseStatement(line)

  mTokens.foreach(t => {
    print(t + ", ")
  })


  def parseStatement(line: String): ArrayBuffer[String] = {
    val tokens = new ArrayBuffer[String]
    val chars = new ArrayBuffer[Char]

    line.foreach(c => {
      if (operators.contains(c)) {
        tokens += chars.mkString
        tokens += c.toString
        chars.clear()
      }
      else {
        chars += c
      }
    })
    tokens += chars.mkString
    tokens.filterNot(s => s <= " " || s == "")
  }



}
