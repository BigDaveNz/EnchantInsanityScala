package nz.co.bigdavenz.ei.core.trait

/**
 * Created by David J. Dudson on 11/01/14.
 */
trait Passive {

  private var active:Boolean

  def onActivate(){}

  def onDeactivate(){}

  def toggleActive(){
      active = !active
      if (active.equals(true)){
          onActivate()
      }else{
        onDeactivate()
      }
  }

  var isActive = active
}
