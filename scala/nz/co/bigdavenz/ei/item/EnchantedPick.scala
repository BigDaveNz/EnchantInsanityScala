package nz.co.bigdavenz.ei.item

import net.minecraft.item.{Item, ItemStack}
import nz.co.bigdavenz.ei.core.traits.Enchantable
import net.minecraft.client.renderer.texture.IIconRegister
import nz.co.bigdavenz.ei.lib.Reference

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Enchanted Pickaxe, allows EI Enchantment to be added
 */
class EnchantedPick(pick: Item) extends Item with Enchantable {
  this.setNameFromLang("ei.item:enchantedPickaxe")
  this.setUnlocalizedName("EnchantedPick")
  this.setHarvestLevel("pickaxe", getHarvestLevel(new ItemStack(pick), "pickaxe"))
  this.setNoRepair


  override def registerIcons(iconRegister: IIconRegister) {
    iconRegister.registerIcon(Reference.modId + ":" + getUnlocalizedName)
  }
}
