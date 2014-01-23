package nz.co.bigdavenz.ei.item.weapons

import net.minecraft.item.{ItemStack, Item}
import nz.co.bigdavenz.ei.core.traits.Enchantable
import nz.co.bigdavenz.ei.item.EiItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

/**
 * Created by David J. Dudson on 22/01/14.
 *
 * trait for Enchanted Weapons
 */

trait EiItemWeapon extends EiItem{
  override def onConverted(convertedStack:ItemStack,newStack:ItemStack, owner: EntityPlayer){
    super.onConverted(convertedStack,newStack,owner)
    newStack.stackTagCompound.setInteger("Max Uses", convertedStack.getMaxDamage)
    newStack.stackTagCompound.setInteger("Current Uses", convertedStack.getItemDamage)
    newStack.func_151001_c(owner.getDisplayName + "'s Enchanted " + convertedStack.getDisplayName)
    //todo fix the converted data
    setupEnchantments(newStack)
  }
  override def onCreated(itemStack: ItemStack, world: World, player: EntityPlayer): Unit = {
    super.onCreated(itemStack, world, player)
  }

  override def isFull3D: Boolean = true


}
