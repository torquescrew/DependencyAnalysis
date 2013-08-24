package test.io.utils

import scala.collection.mutable.ArrayBuffer

/**
 * Created with IntelliJ IDEA.
 * User: tobysuggate
 * Date: 19/08/13
 * Time: 8:38 PM
 */
class Statement(line: String) {
  val operators = Array(':',';','!','<','>','{','}','*','(',')','&',',','-','=',' ')
  val doubleOps = Array("--","++","->")
  val mTokens = parseStatement(line)

  mTokens.foreach(t => {
    print(t + " ")
  })

  def isWhite(c: Char): Boolean = c <= ' '


  def parseStatement(line: String): ArrayBuffer[String] = {
    val tokens = new ArrayBuffer[String]
    val chars = new ArrayBuffer[Char]
    val ops = new ArrayBuffer[Char]
//    var lastWasWhite = false

    line.foreach(c => {
      if (operators.contains(c)) {
        if (chars.size > 0) {
          tokens += chars.mkString.trim
          chars.clear()
        }
        if (!isWhite(c))
          ops += c
        if (ops.size == 2) {
          if (doubleOps.contains(ops)) {
            tokens += ops.mkString.trim
          }
          else {
            tokens += ops.head.toString.trim
            tokens += ops.tail.head.toString.trim
          }

          ops.clear()
        }
      }
      else {
        if (ops.size == 1) {
          tokens += ops.mkString.trim
          ops.clear()
        }
        chars += c
      }
    })
    tokens += chars.mkString
    tokens.filterNot(s => s <= " " || s == "")
  }



}
