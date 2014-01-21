package nz.co.bigdavenz.ei.item.tools

import nz.co.bigdavenz.ei.lib.Reference
import nz.co.bigdavenz.ei.EnchantInsanity
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.item.EiItemTool

/**
 * Created by David J. Dudson on 21/01/14.
 *
 * Enchanted Shears Class
 */
class EnchantedShears extends EiItemTool{

  val itemType: String = "Enchanted Shears"

  def EnchantedAxe{
    this.setUnlocalizedName("eishears")
    this.setCreativeTab(EnchantInsanity.tabEi)
  }
  override def onCreate {
    Communicate.withPlayer(getOwner,"Congratulations! You created a pair of Enchanted Shears with the same stats as : " + consumedToolStack.getDisplayName)
  }
}
