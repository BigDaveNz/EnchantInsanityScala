package nz.co.bigdavenz.ei.file.data


/**
 * Created by David J. Dudson on 13/03/14.
 *
 * Handles all primitive Data types
 */
class PrimitiveData(value: Any, identifier: String, locked: Boolean) extends Data(value, identifier, locked) {
  def this(value: Any, identifier: String) {
    this(value, identifier, false)
  }

  override def toXml: xml.Node = <Data type={getXmlIdentifier} value={value.toString}/>
}

case class BooleanData(value: Boolean) extends PrimitiveData(value, "Boolean", false)

case class Intdata(value: Int) extends PrimitiveData(value, "Int", false)

case class FloatData(value: Float) extends PrimitiveData(value, "Float", false)

case class Doubledata(value: Double) extends PrimitiveData(value, "Double", false)

case class ShortData(value: Short) extends PrimitiveData(value, "Short", false)

case class LongData(value: Long) extends PrimitiveData(value, "Long", false)

case class StringData(value: String) extends PrimitiveData(value, "String", false)

case class InvalidData() extends PrimitiveData(None, "None", true)

