package nz.co.bigdavenz.ei.item.tools

import nz.co.bigdavenz.ei.item.EiItemTool
import nz.co.bigdavenz.ei.EnchantInsanity
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.lib.Reference

/**
 * Created by David J. Dudson on 21/01/14.
 *
 * Enchanted Axe class
 */
class EnchantedAxe extends EiItemTool{
  val itemType: String = "Enchanted Axe"

  def EnchantedAxe{
    this.setUnlocalizedName("eiaxe")
    this.setTextureName(Reference.modId + ":" + this.getUnlocalizedName)
    this.setCreativeTab(EnchantInsanity.tabEi)
  }
  override def onCreate {
    Communicate.withPlayer(getOwner,"Congratulations! You created an Enchanted Axe with the same stats as a: " + consumedToolStack.getDisplayName)
  }
}
