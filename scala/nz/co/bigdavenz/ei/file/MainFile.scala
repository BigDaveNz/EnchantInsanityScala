package nz.co.bigdavenz.ei.file

import scala.xml.XML

/**
 * Created by David J. Dudson on 10/02/14.
 *
 * All data is in this file
 */

object MainFile {

  var mainNode: xml.Node = <Data value="mainNode"/>

  def save {
    XML.save("EIData.ei", mainNode)
  }


  def load {
  }
}
