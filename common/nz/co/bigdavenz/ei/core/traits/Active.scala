package nz.co.bigdavenz.ei.core

.trait

/**
 * Created by David J. Dudson on 11/01/14.
 */
trait Active extends Passive{

  private var enabled:Boolean
  var triggered:Boolean

  def onActive(){
    if(enabled){
      onActiveTriggered()
    }
  }

  def onActiveTriggered(){}

  def isEnabled(){
    enabled
  }

  def toggleEnabled()


}
