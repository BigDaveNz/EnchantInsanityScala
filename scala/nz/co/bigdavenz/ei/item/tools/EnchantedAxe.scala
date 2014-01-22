package nz.co.bigdavenz.ei.item.tools

import nz.co.bigdavenz.ei.item.EiItemTool
import nz.co.bigdavenz.ei.EnchantInsanity
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.client.renderer.texture.IIconRegister

/**
 * Created by David J. Dudson on 21/01/14.
 *
 * Enchanted Axe class
 */
class EnchantedAxe extends EiItemTool{
  val itemType: String = "Enchanted Axe"

  override def onCreate {
    Communicate.withPlayer(getOwner,"Congratulations! You created an Enchanted Axe with the same stats as a: " + consumedToolStack.getDisplayName)
  }


  override def registerIcons(iconRegister: IIconRegister): Unit = itemIcon = iconRegister.registerIcon(Reference.modId + ":" + getUnlocalizedName)
}
