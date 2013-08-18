package test.io.utils

import java.io.File
import scala.io.Source

/**
 * Created with IntelliJ IDEA.
 * User: tobysuggate
 * Date: 17/08/13
 * Time: 1:30 PM
 */

object IncludeFunctions {

  def isCpp(file: File): Boolean =
    file.getName.endsWith(".h") || file.getName.endsWith(".cpp")


  def isIncludeStatement(line: String): Boolean =
    line.stripMargin.startsWith("#include")


  def getIncludeFileName(line: String): String = {
    if (line.contains('"')) {
      val i = line.indexOf('"') + 1
      val j = line.lastIndexOf('"')
      line.slice(i, j)
    }
    else {
      val i = line.indexOf('<') + 1
      val j = line.indexOf('>')
      line.slice(i, j)
    }
  }


  def getLines(file: File): Iterator[String] =
    Source.fromFile(file, "ISO-8859-5").getLines()


  def getCode(file: File): String =
    Source.fromFile(file, "ISO-8859-5").mkString

}
