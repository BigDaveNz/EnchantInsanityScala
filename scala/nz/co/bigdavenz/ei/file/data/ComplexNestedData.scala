package nz.co.bigdavenz.ei.file.data


/**
 * Created by David J. Dudson on 13/03/14.
 *
 * used for Int arrays
 */
abstract class ComplexNestedData(value: Any, identifier: String, locked: Boolean) extends Data(value, identifier, locked) {
  //  override def toXml: Node = {
  //      <Data identifier ={this.getXmlIdentifier}>
  //        {this.xmlInner}
  //      <Data/>
  //  }
  //
  //  def xmlInner:Node
  //}
  //
  //case class ArrayData(value: Array[Data], locked: Boolean) extends ComplexNestedData(value,"Array",false) {
  //  override def xmlInner {
  //    value.foreach(a => a.toXml)
  //  }
}
