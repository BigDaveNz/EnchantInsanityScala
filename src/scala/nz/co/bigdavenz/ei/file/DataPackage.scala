package nz.co.bigdavenz.ei.file


import scala.collection.mutable
import com.google.common.base.Defaults

/**
 * Created by David J. Dudson on 13/03/14.
 *
 * Allows Data to be packages and subpackaged
 */
class DataPackage() extends mutable.HashMap[String, Any] {

  def getOfType[T: Manifest](key: String): T = {
    val value = this.getOrElseUpdate(key, Defaults)
    value.getClass.isAssignableFrom(ofClass[T])

  }

  def getDefault(clazz: Class) {
    Defaults.defaultValue(clazz)
  }
}
