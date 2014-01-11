package nz.co.bigdavenz.ei.core.traits

/**
 * Created by David J. Dudson on 11/01/14.
 */
trait Nameable {

  private var name: String = "Default Name"

  def setName(newName:String){
    name = newName
  }

  val getName = name
}
