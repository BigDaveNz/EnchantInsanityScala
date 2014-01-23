package nz.co.bigdavenz.ei.item.tools

import net.minecraft.item._
import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.item.EiItem
import net.minecraft.world.World

/**
 * Created by David J Dudson on 21/01/14.
 *
 * Trait for handling tools created via Ei Conversion
 */
trait EiItemTool extends EiItem {

  //  override def getHarvestLevel(itemStack: ItemStack, toolClass: String): Int = {
  //  }
  // Todo Do this the EI way, using adaptability of what exactly can be harvested

  override def isItemTool(itemStack: ItemStack): Boolean = true

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

  //  override def hitEntity(itemStack: ItemStack, attackedEntity: EntityLivingBase, attacker: EntityLivingBase): Boolean = {
  //    this.consumedTool.hitEntity(itemStack, attackedEntity, attacker)
  //
  //  }
  //  TODO do this the EI way, getting player data aswell as tool data

  //  itemStack.stackTagCompound.setString("ConsumedToolName", consumedTool.getItemStackDisplayName())
  //  override def onCreated(itemStack: ItemStack, world: World, player: EntityPlayer, consumedToolStack:ItemStack){
  //    super.onCreated(itemStack,world,player)
  //  }
  // todo decide whether or not we need this function

}
