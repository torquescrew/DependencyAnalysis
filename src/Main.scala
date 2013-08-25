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
  val chartdrawer = new File("/Users/tobysuggate/Desktop/LCWM4/LabChartEssentials/LabChart/ChartDraw/ChartDrawer.cpp")


  FindTypes.parseFile(chartdrawer)

}

