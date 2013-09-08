package test.io.utils

import java.io.File
import IncludeFunctions._
import scala.collection.mutable.ArrayBuffer

/**
 * Created with IntelliJ IDEA.
 * User: Toby Suggate
 * Date: 17/08/13
 * Time: 1:04 PM
 */
object FindTypes {
//  val kClass     = "class"
//  val kStruct    = "struct"
//  val kTypedef   = "typedef"
//  val kEnum      = "enum"
//  val kEnumClass = "enum class"
//  val typeDecKeywords = (kClass, kStruct, kTypedef, kEnum, kEnumClass)


  def parseFile(file: File) {
    val statements = FindTypes.fileToStatements(file)

    statements.foreach(s => {
      if (!s.isEmpty) {
        val ns = new Statement(s)
        ns.printTokens()
        if (ns.isTypeDeclaration) {
//          println(ns.getDeclaredType /* + " from: " + s*/)
        }
      }
    })
  }


  def fileToStatements(file: File): ArrayBuffer[String] = {
    linesToStatements(getLines(file))
  }


  def words(line: String) = line.split(" +")


//  def getType(typeDec: String): String = {
//    val t = getType_(words(typeDec).head, typeDec)
//    if ((t.last == ';') || t.last == '{') {
//      t.init
//    }
//    else
//      t
//  }


//  def getType_(keyword: String, typeDec: String): String = keyword match {
//    case `kClass` => words(typeDec).tail.head
//    case `kStruct` => words(typeDec).tail.head
//    case `kTypedef` => words(typeDec).last
//    case `kEnum` => words(typeDec).tail.head
//    case `kEnumClass` => words(typeDec).tail.head // TODO: enum class is two words!
//  }


  def linesToStatements(lines: Iterator[String]): ArrayBuffer[String] = {
    codeToStatements2(linesToCode(lines))
  }


  def codeToStatements(code: String): ArrayBuffer[String] = {
    val statements = new ArrayBuffer[String]
    val statement = new ArrayBuffer[Char]

    code.foreach(c => {
      if (c.equals(';') || c.equals('{') || c.equals('}')) {
        statement += c
        statements += statement.mkString
        statement.clear()
      }
      else if (c.equals('#')) {
        statements += statement.mkString
        statement.clear()
        statement += c
      }
      else {
        statement += c
      }
    })

    statements
  }


  def codeToStatements2(code: String): ArrayBuffer[String] = {
    val statements = new ArrayBuffer[String]
    val statement = new ArrayBuffer[Char]

    var tail = code
    while (tail.size > 0) {
      val c = tail.head
      if (c.equals(';') || c.equals('{') || c.equals('}')) {
        statement += c
        statements += statement.mkString
        statement.clear()
      }
      else if (c.equals('#')) {
        statements += statement.mkString
        statement.clear()
        statement += c
      }
      else {
        statement += c
      }
      tail = tail.tail
    }


    statements
  }


  def linesToCode(lines: Iterator[String]): String = {
    val code = new ArrayBuffer[String]

    lines.foreach(line => {
      code += RemoveComments.removeComments(line)
    })

    code.mkString
  }


//  def removeComments(line: String): String = {
//    var removed = line
//    while(containsMultiLineComment(removed)) {
//      removed = removeMultiLineComments(removed)
//    }
//    removeSingleLineComments(removed)
//  }
//
//
//  def removeSingleLineComments(line: String): String = {
//    val i = line.indexOf("//")
//
//    if (i >= 0) {
//      line.take(i)
//    }
//    else
//      line
//  }
//
//
//  def removeMultiLineComments(line: String): String = {
//    val j = line.indexOf("/*")
//    val k = line.indexOf("*/")
//    if (j >= 0) {
//      if (k >= 0) {
//        line.take(j) + line.substring(k+2)
//      }
//      else {
//        line.take(j)
//      }
//    }
//    else if (k >= 0) {
//      line.substring(k+2)
//    }
//    else
//      line
//  }
//
//
//  def containsMultiLineComment(line: String): Boolean = {
//    line.indexOf("/*") >= 0 || line.indexOf("*/") >= 0
//  }


//  def isTypeDeclaration(statement: ArrayBuffer[String]): Boolean = {
//    !statement.isEmpty && typeDecKeywords.productIterator.contains(statement.head) && (statement.size > 2 || statement.last == "{")
//  }
//
//
//  def hasTypeDecKeyword(line: String): Boolean =
//    typeDecKeywords.productIterator.contains(line.split(" +").head)

}
