package nz.co.bigdavenz.ei.item.tools

import net.minecraft.client.renderer.texture.IIconRegister
import nz.co.bigdavenz.ei.lib.Reference


/**
 * Created by David J. Dudson on 21/01/14.
 *
 * Enchanted Pickaxe class
 */
class EnchantedPickaxe extends EiItemTool {
  override def registerIcons(iconRegister: IIconRegister): Unit = itemIcon = iconRegister.registerIcon(Reference.modId + ":" + getUnlocalizedName)

}
