package nz.co.bigdavenz.ei.file.data

import scala.collection.mutable.HashMap

import nz.co.bigdavenz.ei.core.chat.Communicate

/**
 * Created by David J. Dudson on 13/03/14.
 *
 * Allows Data to be packages and subpackaged
 */
class DataPackage(key: String, value: HashMap[String, Data]) extends Data(value, true) {

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

  def getContentsSafetly(key: String) {
    if (this.containsKey(key)) {
      getContents(key)
    } else {
      Communicate.withConsole("Attempted to get contents which doesnt exist!")
      None
    }
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
}
