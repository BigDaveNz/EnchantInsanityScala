package nz.co.bigdavenz.ei.processors

import cpw.mods.fml.relauncher.{SideOnly, Side}
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.command.{CommandBase, ICommandSender, WrongUsageException}
import net.minecraft.entity.player.EntityPlayerMP
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.item.{EiItem, EiItems}
import net.minecraft.item.{ItemShears, ItemTool, ItemStack}

/**
 * Created by David J. Dudson on 6/01/14.
 *
 * processes all commands
 */

object CommandProcessor extends CommandBase {

  def getCommandName = {
    Reference.modId
  }

  override def canCommandSenderUseCommand(commandSender: ICommandSender): Boolean = {
    true
  }

  @SideOnly(Side.CLIENT)
  def processCommand(commandSender: ICommandSender, args: Array[String]) {
    if (args.length > 0) {
      val commandName: String = args(0)
      System.arraycopy(args, 1, args, 0, args.length - 1)
      commandName.toLowerCase match {
        case "debugtoggle" =>
          Reference.debugMode = !Reference.debugMode
          Communicate.withCommandSender(commandSender, "Debug mode toggled to: " + Reference.debugMode)
        case "debugcheck" =>
          Communicate.withCommandSender(commandSender, "Debug mode is: " + Reference.debugMode)
        case "version" =>
          Communicate.withCommandSender(commandSender, "Version: " + Reference.modVersion)
        case "convert" =>
          convert(commandSender)
        case _ =>
          throw new WrongUsageException("Invalid Usage [skill|leaderboard|debugtoggle|debugcheck|version|convert]", new Array[Nothing](0))
      }
    }
  }

  def convert(commandSender: ICommandSender) {

    val player = commandSender.asInstanceOf[EntityPlayerMP]

    if (player.inventory.getCurrentItem == null) {
      Communicate.withPlayer(player,"What are you trying to do??? Convert air!")
    } else {
      val converted: ItemStack = player.inventory.getCurrentItem
      val currentSlot: Int = player.inventory.currentItem


      converted.getItem match {

        case _: EiItem => {
          Communicate.withPlayer(player, "This item has already been converted!")
        }
        case _: ItemShears => {
          convertTool(player, converted, currentSlot)
        }

        case _: ItemTool => {
          convertTool(player, converted, currentSlot)
        }
        case _ =>
          Communicate.withPlayer(player, "Cannot convert Item: Invalid Type")

      }
    }
  }

  def convertTool(player: EntityPlayerMP, converted: ItemStack, currentSlot: Int) {
    val toolStack: ItemStack = EiItems.createEiTool(converted, player)
    if (toolStack != null) {
      player.inventory.decrStackSize(currentSlot, 1)
      player.inventory.setInventorySlotContents(currentSlot, toolStack)
    }
  }

  def getCommandUsage(var1: ICommandSender): String = "ei.command.usage:ei "
}
