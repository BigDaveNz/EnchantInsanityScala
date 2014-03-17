package nz.co.bigdavenz.ei.file.data

import nz.co.bigdavenz.ei.core.chat.Communicate

/**
 * Created by David J. Dudson on 12/03/14.
 *
 * Main data class all others inherit from
 */
@SerialVersionUID(333)
abstract class Data(private var value: Any, private val identifier: String, private var locked: Boolean) extends Serializable {

  val isLocked: Boolean = locked
  val getValue = value
  val getXmlIdentifier: String = identifier

  def modifyValue(newValue: Any) {
    if (!locked && value.getClass == newValue.getClass) this.value = newValue
    else Communicate.withConsole("Data attempted to be modified with invalid type")
  }

  def setLocked(lock: Boolean) {
    this.locked = lock
  }

  def toXml: xml.Node
}


