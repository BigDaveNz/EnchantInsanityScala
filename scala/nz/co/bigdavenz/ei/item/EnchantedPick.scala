package nz.co.bigdavenz.ei.item

import net.minecraft.item.{Item, ItemStack, ItemPickaxe}
import nz.co.bigdavenz.ei.core.traits.Enchantable
import net.minecraft.client.renderer.texture.IIconRegister
import nz.co.bigdavenz.ei.lib.Reference
import nz.co.bigdavenz.ei.EnchantInsanity

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Enchanted Pickaxe, allows EI Enchantment to be added
 */
class EnchantedPick(pick: Item) extends ItemPickaxe(pick.asInstanceOf[ItemPickaxe].func_150913_i()) with Enchantable {
  this.setNameFromLang("ei.item:enchantedPickaxe")
  this.setMaxStackSize(1)
  this.setUnlocalizedName("EnchantedPick")
  this.setCreativeTab(EnchantInsanity.tabEi)
  this.setHarvestLevel("pickaxe", getHarvestLevel(new ItemStack(pick), "pickaxe"))
  this.setNoRepair

  override def registerIcons(iconRegister: IIconRegister) {
    iconRegister.registerIcon(Reference.modId + ":" + getUnlocalizedName)
  }
}
