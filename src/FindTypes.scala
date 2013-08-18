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
  val typeDecKeywords = ("class", "struct", "typedef", "enum", "enum class")

  def run() {
    val mFile = new File("/Users/tobysuggate/Documents/Repos/LCMMDev/LabChart/PCDevelop/LabChart/ChartLib/RecordSelectorPane.h")
    val chartdrawer = new File("/Users/tobysuggate/Desktop/LCWM4/LabChartEssentials/LabChart/ChartDraw/ChartDrawer.h")

    parseFile(chartdrawer)
  }

  def parseFile(file: File) {
    val lines = getLines(file)
    val code = linesToStatements(lines)

    code.mkString.lines.foreach(line => {
      isTypeDeclaration(line.stripPrefix(" "))
    })

    println("Num lines: " + code.size)
  }


  def linesToStatements(lines: Iterator[String]): ArrayBuffer[String] = {
    val code = new ArrayBuffer[String]

    lines.foreach(line => {
      val a = line.replace(";", ";\n").replace("{","{\n").split(" +")
      code += a.mkString(" ")
    })

    code
  }


  def isTypeDeclaration(statement: String): Boolean = {
    val words = statement.split(" ")

    if (words.size > 2 && hasTypeDecKeyword(statement)) {
      println("Yes: " + statement)
      true
    }
    else if (words.last.contains("{")) {
      println("Yes: " + statement)
      true
    }
    else {
      println("No: " + statement)
      false
    }
  }


  def hasTypeDecKeyword(line: String): Boolean =
    typeDecKeywords.productIterator.contains(line.split(" +").head)

}
