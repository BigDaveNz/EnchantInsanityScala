package nz.co.bigdavenz.ei.file.data

import nz.co.bigdavenz.ei.core.chat.Communicate

/**
 * Created by David J. Dudson on 12/03/14.
 *
 * Main data class all others inherit from
 */
@SerialVersionUID(333)
abstract class Data(private var value: Any, locked: Boolean) extends Serializable {

  def modifyValue(newValue: Any) {
    if (!locked && value.getClass == newValue.getClass) this.value = newValue
    else Communicate.withConsole("Data attempted to be modified with invalid type")
  }

  def isLocked = locked

  def getValue = value
}
