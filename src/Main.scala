package test.io.utils

import java.io.File


/**
 * Created with IntelliJ IDEA.
 * User: tobysuggate
 * Date: 17/08/13
 * Time: 1:37 PM
 */

object Main extends App {

  val file = new File("/Users/tobysuggate/Desktop/LCWM4/Libs/CEF/include/internal/cef_types_wrappers.h")

  val statements = FindTypes.fileToStatements(file)

  statements.foreach(s => {
    if (FindTypes.isTypeDeclaration(s)) {
//      new Statement(s)
//      println()
    }
    if (!s.isEmpty) {
      new Statement(s)
      println()
    }

  })

}

