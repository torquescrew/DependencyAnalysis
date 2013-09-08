/**
 * Created with IntelliJ IDEA.
 * User: tobysuggate
 * Date: 8/09/13
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */

package test.io.utils

object RemoveComments {

  def removeComments(line: String): String = {
    var removed = line
    while(containsMultiLineComment(removed)) {
      removed = removeMultiLineComments(removed)
    }
    removeSingleLineComments(removed)
  }


  def removeSingleLineComments(line: String): String = {
    val i = line.indexOf("//")

    if (i >= 0) {
      line.take(i)
    }
    else
      line
  }


  def removeMultiLineComments(line: String): String = {
    val j = line.indexOf("/*")
    val k = line.indexOf("*/")
    if (j >= 0) {
      if (k >= 0) {
        line.take(j) + line.substring(k+2)
      }
      else {
        line.take(j)
      }
    }
    else if (k >= 0) {
      line.substring(k+2)
    }
    else
      line
  }


  def containsMultiLineComment(line: String): Boolean = {
    line.indexOf("/*") >= 0 || line.indexOf("*/") >= 0
  }
}
