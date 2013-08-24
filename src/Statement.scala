package test.io.utils

import scala.collection.mutable.ArrayBuffer

/**
 * Created with IntelliJ IDEA.
 * User: tobysuggate
 * Date: 19/08/13
 * Time: 8:38 PM
 */
class Statement(line: String) {
  val operators = Array(':',';','!','<','>','{','}','*','(',')','&',',','-','=',' ','.')
  val doubleOps = Array("--","++","->","==")
  val mTokens = parseStatement(line)

  mTokens.foreach(t => {
    print(t + " ")
  })

  def isWhite(c: Char): Boolean = c <= ' '


  def parseStatement(line: String): ArrayBuffer[String] = {
    val tokens = new ArrayBuffer[String]
    val chars = new ArrayBuffer[Char]

    var tail = line
    while (tail.size > 0) {
      val op = tail.take(2)
      if (doubleOps.contains(op)) {
        tokens += chars.mkString
        chars.clear()
        tokens += op
        tail = tail.tail.tail
      }
      else if (operators.contains(tail.head)) {
        tokens += chars.mkString
        chars.clear()
        val head = tail.head
        if (!isWhite(head)) {
          tokens += head.toString
        }
        tail = tail.tail
      }
      else {
        chars += tail.head
        tail = tail.tail
      }
    }

    tokens += chars.mkString
    tokens.filterNot(s => s <= " " || s == "")
  }



}
