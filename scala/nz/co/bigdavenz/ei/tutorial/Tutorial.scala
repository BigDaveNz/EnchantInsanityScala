package nz.co.bigdavenz.ei.tutorial

import nz.co.bigdavenz.ei.client.gui.Popup
import nz.co.bigdavenz.ei.file.data.{PrimitiveData, DataPackage}
import net.minecraft.entity.player.EntityPlayer

/**
 * Created by David J. Dudson on 14/03/14.
 *
 * Teach people stuff
 */
abstract class Tutorial(key: String, popup: Popup) extends DataPackage(key) {

  this.appendContents("Complete", new PrimitiveData(s))


  def startTutorial(player: EntityPlayer) {

  }
}
