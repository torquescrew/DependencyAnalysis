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
  val chartdrawer = new File("/Users/tobysuggate/Desktop/LCWM4/LabChartEssentials/LabChart/ChartDraw/ChartDrawer.h")

  val typeDecKeywords = ("class", "struct", "typedef", "enum", "enum class")

  def run() {
//    parseFile()
    val code = new ArrayBuffer[String]

    getLines(chartdrawer).foreach(line => {
      val a = line.replace(";", ";\n").replace("{","{\n").split(" +")
      code += a.mkString(" ")
    })

//    println(code.mkString)
    code.mkString.lines.foreach(line => {
      isTypeDeclaration(line.stripPrefix(" "))
    })
  }

  def parseFile() {
    codeToStatements(getCode(file)).foreach(s => {
      isTypeDeclaration(s)
//      println(s)
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
//    val statement = new ArrayBuffer[String]

    lines.foreach(line => {
      val a = line.replace(";", ";\n").replace("{","{\n").split(" +")
      statements += a.mkString(" ").stripMargin
    })

    statements
  }


  def isTypeDeclaration(statement: String): Boolean = {
    val words = statement.split(" ")

    if (words.size > 2 && hasTypeDecKeyword(statement)) {
      println("Yes: " + statement)
      true
    }
    else {
      println("No: " + statement)
      false
    }
  }


  def codeToStatements(code: String) = {
    code.split(Array(';','{','}'))
  }


  def isTerminated(line: String): Boolean = {
    line.contains(";") || line.contains("{")
  }


  def hasTypeDecKeyword(line: String): Boolean =  {
    //typeDecKeywords.productIterator.contains(word)
//    val b = typeDecKeywords.productIterator.contains(line.split(" +").head)
//
//    if (b) {
//      println("Yes: " + line)
//      true
//    }
//    else {
//      println("No: " + line)
//      false
//    }
    typeDecKeywords.productIterator.contains(line.split(" +").head)
//    typeDecKeywords.productIterator.exists(word.contains)
  }

}
