package test.io.utils

import scala.collection.mutable.ArrayBuffer
import java.util.StringTokenizer


/**
 * Created with IntelliJ IDEA.
 * User: tobysuggate
 * Date: 17/08/13
 * Time: 1:37 PM
 */

object Main extends App {
//  var depends = new Depends

  FindTypes.run()

//  val code = "class WindowBase : public WindowBaseMin\n{\npublic:\n   //virtual ~WindowBase() \n   //   {\n   //   } \n   virtual void SetPosition(const TRect &size) = 0;\n   virtual bool IsWindowEnabled() const = 0;\n   virtual bool EnableWindow(bool enable = true) = 0;\n};"
//  code.stripLineEnd.replace(";", ";~~").replace("{", "{~~").split("~~").foreach(line => {
//    println(line + "/endline")
//  })


//  code.replace("\n", " ")


//
//  val st = new StringTokenizer("class WindowBase : public WindowBaseMin\n{\npublic:\n   //virtual ~WindowBase() \n   //   {\n   //   } \n   virtual void SetPosition(const TRect &size) = 0;\n   virtual bool IsWindowEnabled() const = 0;\n   virtual bool EnableWindow(bool enable = true) = 0;\n};", ";", true)
//  while (st.hasMoreTokens) {
//    println(st.nextToken())
//  }





  def breakStr(string: String): ArrayBuffer[String] = {
    val strings = new ArrayBuffer[String]

    string.indexOf(';')

    strings
  }
}

