package nz.co.bigdavenz.ei.file


import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

/**
 * Created by David J. Dudson on 13/03/14.
 *
 * Allows Data to be packaged and subPackaged recursively
 */
class DataPackage() extends mutable.HashMap[String, Any] {

  implicit val i: Int = 0
  implicit val d: Double = 0.0D
  implicit val f: Float = 0.0F
  implicit val sh: Short = 0
  implicit val l: Long = 0
  implicit val s: String = ""
  implicit val p: DataPackage = new DataPackage

  //get or set as default(returning default value) HashMap value based on value Higher Type
  private def getOfType[T](key: String)(implicit default: ClassTag[T]): Either[ArrayBuffer[T], T] = {
    val value = this.getOrElseUpdate(key, default)
    val rValue: Either[ArrayBuffer[T], T] = value match {
      case _: T if value.isInstanceOf[DataPackage] => Right(value.asInstanceOf[T])
      case _: T =>
        val ab: ArrayBuffer[T] = new ArrayBuffer[T]
        ab.append(value.asInstanceOf[T])
        Left(ab)
      case _: ArrayBuffer[T] => Left(value.asInstanceOf[ArrayBuffer[T]])
      case _ => throw new IllegalArgumentException("attempted to get a key/set of invalid type")
    }
    rValue
  }

  def setSingleOfType[T](key: String, value: T) {
    this.getOrElseUpdate(key, value)
  }

  def addValueToArrayOfType[T: ClassTag](key: String, value: T) {
    val newArrayBuffer = new ArrayBuffer[T]
    newArrayBuffer.append(value)
    getArrayOfType[T](key) foreach {
      v: T => newArrayBuffer.append(v)
    }
    //Todo make this so it replaces the head and not the whole arraybuffer
  }

  def getArrayOfType[T](key: String)(implicit default: ClassTag[T]): ArrayBuffer[T] = {
    getOfType(key)(default).left.get
  }

  def getSingleOfType[T](key: String)(implicit default: ClassTag[T]): T = {
    getOfType(key)(default).right.get
  }

  def getHeadOfType[T](key: String)(implicit default: ClassTag[T]): T = {
    val v: ArrayBuffer[T] = getArrayOfType(key)(default)
    v.head
  }
}
