package nz.co.bigdavenz.ei.tutorial

import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.client.gui.Popup
import nz.co.bigdavenz.ei.file.DataPackage

/**
 * Created by David J. Dudson on 14/03/14.
 *
 * Teach people stuff
 */
abstract class Tutorial(key: String, popup: Popup) extends DataPackage() {


  def startTutorial(player: EntityPlayer) {

  }
}
