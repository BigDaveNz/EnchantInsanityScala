package nz.co.bigdavenz.ei.file

import scala.collection.mutable.HashMap

import nz.co.bigdavenz.ei.core.chat.Communicate
import scala.collection.mutable

/**
 * Created by David J. Dudson on 13/03/14.
 *
 * Allows Data to be packages and subpackaged
 */
class DataPackage() {
  val value: HashMap[String, Any] = new mutable.HashMap[String, Any]()

  def test {
    this.value.foreach(p =>
      Communicate.withConsole(p._1 + " " + p._2))

  }

  def getMap = value

  def setContents(key: String, data: Any) {
    this.value.put(key, data)
  }

  def removeContents(key: String) {
    this.value.remove(key)
  }

  def containsKey(key: String): Boolean = {
    this.value.contains(key)
  }

  def getContents(key: String) = {
    this.value.get(key).get
  }

  def getPackage(key: String): DataPackage = {
    if (this.containsKey(key)) {
      val pack: Any = this.getContents(key)
      pack match {
        case _: DataPackage =>
          pack.asInstanceOf[DataPackage]
        case _ =>
          createPackage(key)
          new DataPackage
      }
    } else {
      createPackage(key)
      new DataPackage
    }
  }

  def createPackage(key: String) {
    setContents(key, new DataPackage)
  }

  def replaceContents(key: String, value: Any) {
    if (this.value.contains(key)) {
      this.value.put(key, value)
    } else {
      this.setContents(key, value)
    }
  }
}
