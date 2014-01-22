package nz.co.bigdavenz.ei.item.tools

import nz.co.bigdavenz.ei.EnchantInsanity
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.client.renderer.texture.IIconRegister
import nz.co.bigdavenz.ei.item.tools.EiItemTool
import net.minecraft.item.ItemStack

/**
 * Created by David J. Dudson on 21/01/14.
 *
 * Enchanted Axe class
 */
class EnchantedAxe extends EiItemTool{
  val itemType: String = "Enchanted Axe"

  override def onCreate(itemStack:ItemStack) {
    Communicate.withPlayer(getOwner(itemStack),"Congratulations! You created an Enchanted Axe with the same stats as a: " + )
  }


  override def registerIcons(iconRegister: IIconRegister): Unit = itemIcon = iconRegister.registerIcon(Reference.modId + ":" + getUnlocalizedName)
}
