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
//  val wor


  def parseFile(file: File) {
    val statements = FindTypes.fileToStatements(file)

    statements.foreach(s => {
      if (!s.isEmpty) {
        val ns = new Statement(s)
//        ns.printTokens()
//        if (ns.isTypeDeclaration) {
//          println(ns.getDeclaredType /* + " from: " + s*/)
//        }
        printStatement(ns)
      }
    })
  }


  def printStatement(s: Statement) {
    var all = true
//    all = false

    if (all) {
      s.printTokens()
    }
    else {
      if (s.isTypeDeclaration) {
        println(s.getDeclaredType /* + " from: " + s*/)
      }
    }
  }


  def fileToStatements(file: File): ArrayBuffer[String] = {
    linesToStatements(getLines(file))
  }


  def words(line: String) = line.split(" +")


  def linesToStatements(lines: Iterator[String]): ArrayBuffer[String] = {
    codeToStatements(linesToCode(lines))
  }



  def codeToStatements(code: String): ArrayBuffer[String] = {
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
      else if (c.equals('"') && statement.startsWith("#include ") && statement.size > 11) {
        statement += c
        statements += statement.mkString
        statement.clear()
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

}
