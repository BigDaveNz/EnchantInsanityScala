package nz.co.bigdavenz.ei.item.tools

import net.minecraft.item._
import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.core.chat.Communicate
import net.minecraft.entity.EntityLivingBase
import nz.co.bigdavenz.ei.item.EiItem
import net.minecraft.world.World
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.server.MinecraftServer

/**
 * Created by David J Dudson on 21/01/14.
 *
 * Trait for handling tools created via Ei Conversion
 */
trait EiItemTool extends EiItem {

  //  override def getHarvestLevel(itemStack: ItemStack, toolClass: String): Int = {
  //  }
  // Todo Do this the EI way, using adabtability of what exactly can be harvested

  override def isItemTool(itemStack: ItemStack): Boolean = true

  override def onConverted(itemStack:ItemStack,owner: EntityPlayer){
    super.onConverted(itemStack,owner)
    itemStack.stackTagCompound.setBoolean("isTool", true)
    setupEnchantments(itemStack)
  }

  override def onCreated(itemStack: ItemStack, world: World, player: EntityPlayer): Unit = {
    super.onCreated(itemStack, world, player)
    itemStack.getItem.setFull3D
  }

  override def shouldRotateAroundWhenRendering(): Boolean = true

  //  override def hitEntity(itemStack: ItemStack, attackedEntity: EntityLivingBase, attacker: EntityLivingBase): Boolean = {
  //    this.consumedTool.hitEntity(itemStack, attackedEntity, attacker)
  //
  //  }
  //  TODO do this the EI way, getting player data aswell as tool data

  //  itemStack.stackTagCompound.setString("ConsumedToolName", consumedTool.getItemStackDisplayName())
  //  override def onCreated(itemStack: ItemStack, world: World, player: EntityPlayer, consumedToolStack:ItemStack){
  //    super.onCreated(itemStack,world,player)
  //  }
  // todo decide whether or not we need this funciton

}
