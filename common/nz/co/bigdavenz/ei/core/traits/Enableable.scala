package nz.co.bigdavenz.ei.core.traits

/**
 * Created by David J. Dudson on 11/01/14.
 */
trait Enableable {

  private var enabled:Boolean = true

  var getEnabled = enabled

  def enable(){enabled = true}

  def disable(){enabled = false}

}
