package nz.co.bigdavenz.ei.item.weapons

import net.minecraft.client.renderer.texture.IIconRegister
import nz.co.bigdavenz.ei.lib.Reference

/**
 * Created by David J. Dudson on 23/01/14.
 *
 * Enchanted Sword Class
 */

class EnchantedSword extends EiItemWeapon{
  override def registerIcons(iconRegister: IIconRegister): Unit = itemIcon = iconRegister.registerIcon(Reference.modId + ":" + getUnlocalizedName)
}
