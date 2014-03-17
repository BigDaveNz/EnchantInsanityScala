package nz.co.bigdavenz.ei.file.data

import scala.collection.mutable.HashMap

import nz.co.bigdavenz.ei.core.chat.Communicate
import scala.xml.Node

/**
 * Created by David J. Dudson on 13/03/14.
 *
 * Allows Data to be packages and subpackaged
 */
class DataPackage(key: String, value: HashMap[String, Data]) extends ComplexNestedData(value, "DataPackage", true) {

  override def toXml: Node =
    <DataPackage key={this.key}>
      <Test>This is a test</Test>
    </DataPackage>

  def onCreate() {}

  def this(key: String) {
    this(key, new HashMap[String, Data].empty)
  }

  def appendContents(key: String, data: Data) {
    this.value.put(key, data)
  }

  def removeContents(key: String) {
    this.value.remove(key)
  }

  def containsKey(key: String): Boolean = {
    this.value.contains(key)
  }

  def getContents(key: String): Data = {
    this.value.get(key).get
  }

  def replaceContents(key: String, value: Data) {
    if (this.value.contains(key)) {
      if (this.getContents(key).isLocked) {
        Communicate.withConsole("Attempted to modify locked data")
      } else {
        this.value.put(key, value)
      }
    } else {
      this.appendContents(key, value)
    }
  }


  //  override def xmlInner {
  //  this.value.foreach{case (key,value) => <Map key = {key} value ={value.asInstanceOf[Data].toXml} />
}
