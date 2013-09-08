package test.io.utils

import scala.collection.mutable.ArrayBuffer

/**
 * Created with IntelliJ IDEA.
 * User: tobysuggate
 * Date: 19/08/13
 * Time: 8:38 PM
 */
class Statement(line: String) {
  val typeDecKeywords = ("class", "struct", "typedef", "enum", "enum class")
  val operators = Array(':',';','!','<','>','{','}','*','(',')','&',',','-','+','=',' ','.','?')
  val doubleOps = Array("--","++","->","==","<=",">=","::","&&","||","**","/=","!=","+=","-=","*=","()")
  val mTokens = parseStatement(line)


  def isWhite(c: Char): Boolean = c <= ' '


  def parseStatement(line: String): ArrayBuffer[String] = {
    val tokens = new ArrayBuffer[String]
    val chars = new ArrayBuffer[Char]
    var insideStr = false

    var tail = line
    while (tail.size > 0) {
      if (tail.head.equals('"')) {
        insideStr = !insideStr
      }

      val op = tail.take(2)
      if (!insideStr && doubleOps.contains(op)) {
        tokens += chars.mkString
        chars.clear()
        tokens += op
        tail = tail.tail.tail
      }
      else if (!insideStr && operators.contains(tail.head)) {
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


  def printTokens() {
    mTokens.foreach(t => {
      print(t + " ")
    })
    println()
  }


  def isTypeDeclaration: Boolean = {
    isTypeDeclaration(mTokens)
  }


  def isTypeDeclaration(statement: ArrayBuffer[String]): Boolean = {
    !statement.isEmpty && typeDecKeywords.productIterator.contains(statement.head) && (statement.size > 2 || statement.last == "{")
  }


  /*
   * TODO: need concept of global and local type declarations
   */
  def getDeclaredType: String = mTokens.head match {
    case "class" => mTokens.tail.head
    case "struct" => mTokens.tail.head
    case "typedef" => mTokens.init.last
    case "enum" => {
      if (mTokens.tail.head == "class") {
        mTokens.tail.tail.head
      }
      else if (mTokens.tail.head == "{") {
        "constants in enum" //TODO: enum constant definitions. Exist in this file only?
      }
      else
        mTokens.tail.head
    }
  }
}
