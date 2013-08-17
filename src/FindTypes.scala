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
  val file = new File("/Users/tobysuggate/Documents/Repos/LCMMDev/LabChart/PCDevelop/LabChart/ChartLib/RecordSelectorPane.h")

  val typeDecKeywords = ("class", "struct", "typedef", "enum", "enum class")

  def run() {
    parseFile()
  }

  def parseFile() {
    codeToStatements(getCode(file)).foreach(s => {
      println(s)
    })

//    println(getCode(file))
//    println(getLines(file))

//    getLines(file).foreach(line => {
//      println(line)
//    })

//    getLines(file).foreach(line => {
//      println(line)
//    })
  }


  def linesToStatements(lines: Iterator[String]): ArrayBuffer[String] = {
    val statements = new ArrayBuffer[String]
    val statement = new ArrayBuffer[String]

    lines.foreach(line => {
      if (isTerminated(line)) {

      }

      if (line.indexOf(';') > 0) {

      }

    })
    statements
  }


  def codeToStatements(code: String) = {
    code.split(Array(';','{','}'))
  }


  def isTerminated(line: String): Boolean = {
    line.contains(";") || line.contains("{")
  }


  def isTypeDecKeyword(word: String): Boolean =
    typeDecKeywords.productIterator.contains(word)
}
