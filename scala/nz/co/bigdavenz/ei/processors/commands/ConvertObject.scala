package nz.co.bigdavenz.ei.processors.commands

import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.{EntityPlayer, EntityPlayerMP}
import nz.co.bigdavenz.ei.core.chat.Communicate
import net.minecraft.item.{ItemTool, ItemShears, ItemSword, ItemStack}
import nz.co.bigdavenz.ei.item.{EiItems, EiItem}

/**
 * Created by David J. Dudson on 10/02/14.
 *
 * Handles Conversion of all objects
 */
object ConvertObject {

  def convert(commandSender: ICommandSender) {

    val player = commandSender.asInstanceOf[EntityPlayerMP]

    if (player.inventory.getCurrentItem == null) {
      Communicate.withPlayer(player, "What are you trying to do??? Convert air!")
    } else {
      val converted: ItemStack = player.inventory.getCurrentItem
      val currentSlot: Int = player.inventory.currentItem

      converted.getItem match {

        case _: EiItem => {
          Communicate.withPlayer(player, "This item has already been converted!")
        }
        case _: ItemSword => {
          convertWeapon(player, converted, currentSlot)
        }
        case _: ItemShears | _: ItemTool => {
          convertTool(player, converted, currentSlot)
        }
        case _ =>

          Communicate.withPlayer(player, "Cannot convert Item: Invalid Type: " + converted.getItem.getClass.toString)

      }
    }
  }

  def convertWeapon(player: EntityPlayer, converted: ItemStack, currentSlot: Int) {
    val weaponStack: ItemStack = EiItems.createEiWeapon(converted, player)
    if (weaponStack != null) {
      player.inventory.decrStackSize(currentSlot, 1)
      player.inventory.setInventorySlotContents(currentSlot, weaponStack)
    }
  }

  def convertTool(player: EntityPlayerMP, converted: ItemStack, currentSlot: Int) {
    val toolStack: ItemStack = EiItems.createEiTool(converted, player)
    if (toolStack != null) {
      player.inventory.decrStackSize(currentSlot, 1)
      player.inventory.setInventorySlotContents(currentSlot, toolStack)
    }
  }
}
