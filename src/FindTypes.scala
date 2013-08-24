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
  val kClass     = "class"
  val kStruct    = "struct"
  val kTypedef   = "typedef"
  val kEnum      = "enum"
  val kEnumClass = "enum class"
  val typeDecKeywords = (kClass, kStruct, kTypedef, kEnum, kEnumClass)

  def run() {
    val mFile = new File("/Users/tobysuggate/Documents/Repos/LCMMDev/LabChart/PCDevelop/LabChart/ChartLib/RecordSelectorPane.h")
    val chartdrawer = new File("/Users/tobysuggate/Desktop/LCWM4/LabChartEssentials/LabChart/ChartDraw/ChartDrawer.h")

    parseFile(chartdrawer)
  }

  def parseFile(file: File) {
    val code = linesToStatements(getLines(file))

    code.mkString.lines.foreach(line => {
      if (isTypeDeclaration(line)) {
        val type_ = getType(line)
        println(type_ + " from: " + line)
      }
      else {
//        println("No: " + line)
      }
    })

    println("Num lines: " + code.size)
  }


  def fileToStatements(file: File): ArrayBuffer[String] = {
    linesToStatements(getLines(file))
  }


  def words(line: String) = line.split(" +")


  def getType(typeDec: String): String = {
    val t = getType_(words(typeDec).head, typeDec)
    if ((t.last == ';') || t.last == '{') {
      t.init
    }
    else
      t
  }


  def getType_(keyword: String, typeDec: String): String = keyword match {
    case `kClass` => words(typeDec).tail.head
    case `kStruct` => words(typeDec).tail.head
    case `kTypedef` => words(typeDec).last
    case `kEnum` => words(typeDec).tail.head
    case `kEnumClass` => words(typeDec).tail.head // TODO: enum class is two words!
  }


  def linesToStatements(lines: Iterator[String]): ArrayBuffer[String] = {
    codeToStatements(linesToCode(lines))
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
      else {
        statement += c
      }
    })

    statements
  }


  def linesToCode(lines: Iterator[String]): String = {
    val code = new ArrayBuffer[String]

    lines.foreach(line => {
      code += removeComments(line)
    })

    code.mkString
  }


  def removeComments(line: String): String = {
    val i = line.indexOf("//")
    if (i >= 0) {
      line.take(i)
    }
    else
      line
  }


  def isTypeDeclaration(statement: String): Boolean = {
    val words = statement.split(" ")

    hasTypeDecKeyword(statement) && (words.size > 2 || words.last.contains("{"))
  }


  def isTypeDeclaration(statement: ArrayBuffer[String]): Boolean = {
    typeDecKeywords.productIterator.contains(statement.head) && (statement.size > 2 || statement.last == "{")
  }


  def hasTypeDecKeyword(line: String): Boolean =
    typeDecKeywords.productIterator.contains(line.split(" +").head)

}
