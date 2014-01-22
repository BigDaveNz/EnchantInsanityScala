package nz.co.bigdavenz.ei.item.tools

import nz.co.bigdavenz.ei.item.EiItemTool
import nz.co.bigdavenz.ei.EnchantInsanity
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.client.renderer.texture.IIconRegister

/**
 * Created by David J. Dudson on 21/01/14.
 *
 * Enchanted Shovel Class
 */
class EnchantedShovel extends EiItemTool{
  val itemType: String = "Enchanted Shovel"

  override def onCreate {
    Communicate.withPlayer(getOwner,"Congratulations! You created an Enchanted Shovel with the same stats as a: " + consumedToolStack.getDisplayName)
  }

  override def registerIcons(iconRegister: IIconRegister): Unit = itemIcon = iconRegister.registerIcon(Reference.modId + ":" + getUnlocalizedName)
}
