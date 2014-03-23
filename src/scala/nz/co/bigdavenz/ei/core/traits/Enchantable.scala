package nz.co.bigdavenz.ei.core.traits

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Allows item /block to be enchatable
 */

import nz.co.bigdavenz.ei.enchant.Enchantment
import nz.co.bigdavenz.ei.core.chat.Communicate
import net.minecraft.item.ItemStack
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound

trait Enchantable {

  var extendedStackData: NBTTagCompound = null

  def addToMaxEnchants(itemStack: ItemStack) {
    val maxEnchantments = getMaxEnchantments(itemStack)
    maxEnchantments match {
      case 0 =>
        itemStack.stackTagCompound.setInteger("Max Enchantments", maxEnchantments + 1)
        setSlotStatus(itemStack, 1, 0)
      case x if x <= 10 => itemStack.stackTagCompound.setInteger("Max Enchantments", maxEnchantments + 1)

      case _ => Communicate.withConsole("Attempted to set Max Enchantments to more then 10")
    }
  }

  def getNextEmptySlot(itemStack: ItemStack, slot: Int, player: EntityPlayer): Int = {
    for (x <- 1 to getMaxEnchantments(itemStack)) {
      getSlotStatus(itemStack, x) match {
        case 0 => x
      }
    }
    Communicate.withPlayer(player, "Enchantment Slot's full, theres plenty of ways to get extra enchantment slots! If you already have 10 slots... You already have enough")
    -1
  }

  def getMaxEnchantments(itemStack: ItemStack): Int = {
    itemStack.stackTagCompound.getInteger("Max Enchantments")
  }

  def setupEnchantments(itemStack: ItemStack) {
    itemStack.stackTagCompound.setIntArray("Slot Status", Array.fill(10)(-1))
  }

  def getSlotStatus(itemStack: ItemStack, slot: Int): Int = {
    itemStack.stackTagCompound.getIntArray("Slot Status")(slot)
  }

  def setSlotStatus(itemStack: ItemStack, slot: Int, status: Int) {
    val oldArray: Array[Int] = itemStack.stackTagCompound.getIntArray("Slot Status")
    val oldSlotStatus: Int = oldArray(slot)
    oldArray(slot) = status
    itemStack.stackTagCompound.setIntArray("Slot Status", oldArray)
    oldSlotStatus match {
      case -1 if status == 0 => addEnchantmentSlot(itemStack, slot)
      case 0 if status == -1 => removeEnchantmentSlot(itemStack, slot)
      case 1 if status == -1 => removeEnchantmentSlot(itemStack, slot)
    }
  }

  def removeEnchantmentSlot(itemStack: ItemStack, slot: Int) {
    itemStack.stackTagCompound.removeTag("Enchantment Slot " + slot)
  }

  def addEnchantmentSlot(itemStack: ItemStack, slot: Int) {
    itemStack.stackTagCompound.setString("Enchantment Slot " + slot, "Empty")
  }

  def addEnchantmentToSlot(itemStack: ItemStack, slot: Int, enchantment: Enchantment, player: EntityPlayer, replace: Boolean = false) {
    levelVerification(enchantment, player) match {
      case true =>
        getSlotStatus(itemStack, slot) match {
          case -1 => Communicate.withConsole("Attempted to add enchantment to disabled slot! Something is not right")
          case 0 => setSlotStatus(itemStack, slot, 1)
          case 1 if replace =>
        }
      case false => Communicate.withPlayer(player, "You dont have the appropriate skill levels for this enchantment")
      //todo reply with actual required levels once level verification is added
    }

    itemStack.stackTagCompound.setString("Enchantment Slot " + slot, enchantment.getName)
  }

  def setMaxEnchantments(itemStack: ItemStack, player: EntityPlayer, max: Int, cheated: Boolean): Boolean = {
    max match {
      case x if x < 10 && x != 0 =>
        itemStack.stackTagCompound.setInteger("Original Max Enchantments", itemStack.stackTagCompound.getInteger("Max Enchantments"))
        itemStack.stackTagCompound.setInteger("Cheated Max Enchantments", max)
        itemStack.stackTagCompound.setInteger("Max Enchantments", max)
        true
      case _ => false
      //todo add cheated to player data
    }
  }

  def getSlotFromEnchantment(itemStack: ItemStack, enchantmentName: String, player: EntityPlayer): Int = {
    for (x <- 1 to getMaxEnchantments(itemStack)) {
      itemStack.stackTagCompound.getString("Enchantment Slot " + x) match {
        case `enchantmentName` => x
        case _ =>
      }
    }
    -1
  }

  def replaceEnchantment(itemStack: ItemStack, enchantment: Enchantment, player: EntityPlayer) {
    getSlotFromEnchantment(itemStack, enchantment.getName, player) match {
      case -1 => Communicate.withPlayer(player, "Didnt find the enchantment to replace, are you sure it exists?")
      case x if x <= 10 => addEnchantmentToSlot(itemStack, x, enchantment, player, true)
    }
  }

  def addExtendedEnchantmentData(itemStack: ItemStack) {
    extendedStackData = new NBTTagCompound
    //todo finish adding the ability for enchantments to store individual data in their own tag
  }

  def removeEnchantment(itemStack: ItemStack, slot: Int) {
    itemStack.stackTagCompound.setString("Enchantment Slot " + slot, "Empty")
  }

  def levelVerification(enchantment: Enchantment, player: EntityPlayer): Boolean = {
    true
    //todo setup proper level verification
  }
}
