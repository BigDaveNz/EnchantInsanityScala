package nz.co.bigdavenz.ei.item.tools

import net.minecraft.client.renderer.texture.IIconRegister
import nz.co.bigdavenz.ei.lib.ModReference


/**
 * Created by David J. Dudson on 21/01/14.
 *
 * Enchanted Shovel Class
 */
class EnchantedShovel extends EiItemTool {
  override def registerIcons(iconRegister: IIconRegister): Unit = itemIcon = iconRegister.registerIcon(ModReference.modId + ":" + getUnlocalizedName)
}
