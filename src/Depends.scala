package test.io.utils

import io.utils.RichFile.toRichFile

// this makes implicit toRichFile active

import java.io.File

import IncludeFunctions._

class Depends {
  val root = new File("/Users/tobysuggate/Desktop/LCWM4")

  var cppFiles: scala.collection.mutable.Map[String, Int] = scala.collection.mutable.Map()
  fillFilesMap(root.andTree)


  fillOccurrences(root.andTree)

  cppFiles.foreach(f => {
    if (f._1.endsWith(".h") && f._2 > 20)
      println(f)
  })


  def addIncludesForFile(file: File) {
    getLines(file).foreach(line => {
      if (isIncludeStatement(line)) {
        val name = getIncludeFileName(line)
        if (cppFiles.contains(name)) {
          cppFiles(name) += 1
        }
      }
    })
  }


  def printAll(tree: Iterable[File], ending: String) {
    tree.foreach(file => {
      if (file.getName.endsWith(ending)) {
        println(file)
      }
    })
  }


  def fillOccurrences(tree: Iterable[File]) {
    tree.foreach(file => {
      if (isCpp(file)) {
        addIncludesForFile(file)
      }
    })
  }


  def fillFilesMap(tree: Iterable[File]) {
    tree.foreach(file => {
      if (isCpp(file)) {
        cppFiles += (file.getName -> 0)
      }
    })
  }

}
