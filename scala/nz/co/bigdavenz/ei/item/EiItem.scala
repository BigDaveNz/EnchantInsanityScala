package nz.co.bigdavenz.ei.item

import net.minecraft.item._
import nz.co.bigdavenz.ei.core.traits.Enchantable
import net.minecraft.client.renderer.texture.IIconRegister
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.core.chat.Communicate
import net.minecraft.util.IIcon
import cpw.mods.fml.relauncher.{Side, SideOnly}
import nz.co.bigdavenz.ei.EnchantInsanity
import net.minecraft.world.World
import net.minecraft.nbt.NBTTagCompound
import java.util
import net.minecraft.server.MinecraftServer

/**
 * Created by David J. Dudson on 15/01/14.'
 *
 * Work Around for EI Items that do not have a specific tool material
 */
trait EiItem extends Item with Enchantable {

  def onConverted(itemStack:ItemStack,owner:EntityPlayer){
    onCreated(itemStack,owner.worldObj, owner)
    itemStack.stackTagCompound.setBoolean("Converted", true)
    itemStack.stackTagCompound.setString("Owner", owner.getDisplayName)
  }

  def getOwnername(itemStack: ItemStack): String = itemStack.stackTagCompound.getString("Owner")

  def getOwner(itemStack: ItemStack): EntityPlayer = {
    itemStack match {
      case null => Communicate.withConsole("Oi!!! Theres No owner for this stack, why are you attempting to get it?") null
      case _ => MinecraftServer.getServer.getConfigurationManager.getPlayerForUsername(itemStack.stackTagCompound.getString("Owner"))
    }
  }

  def setDroppable(itemStack: ItemStack, droppable: Boolean) {
    itemStack.stackTagCompound.setBoolean("Droppable", droppable)
  }

  def getDroppable(itemStack: ItemStack) {
    itemStack.stackTagCompound.getBoolean("Droppable")
  }

  override def onDroppedByPlayer(itemStack: ItemStack, player: EntityPlayer): Boolean = {
    itemStack.stackTagCompound.getBoolean("Converted") match {
      case true => Communicate.withPlayer(getOwner(itemStack), "You dropped an enchanted item?? Really?? Clumsy much!!")
      case _ =>
    }
    itemStack.stackTagCompound.getBoolean("Droppable")
  }

  override def onCreated(itemStack: ItemStack, world: World, player: EntityPlayer) {
    itemStack match {
      case null => itemStack.setTagCompound(new NBTTagCompound)
      case _ =>
    }
    itemStack.stackTagCompound.setString("ItemType", "Unspecified, this is probably an error")
    itemStack.stackTagCompound.setBoolean("Converted", false)
    itemStack.stackTagCompound.setBoolean("Droppable", true)
    itemStack.stackTagCompound.setInteger("Max Enchantments", 0)

    Communicate.withConsoleDebug("onCreated method Called", "Item Creation")
  }


  override def hasEffect(par1ItemStack: ItemStack): Boolean = true

  //  override def addInformation(itemStack: ItemStack, entityPlayer: EntityPlayer, list: util.List[_], par4: Boolean) {
  //    itemStack match {
  //      case null => itemStack.setTagCompound(new NBTTagCompound)
  //      case _ =>
  //    }
  //  }
  // Todo implement this properly, aka get around java list derpyness
}
