package nz.co.bigdavenz.ei.processors

import cpw.mods.fml.relauncher.{SideOnly, Side}
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.command.{ICommand, CommandBase, ICommandSender, WrongUsageException}
import net.minecraft.entity.player.EntityPlayerMP
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.item.EiItemTool
import net.minecraft.item.ItemStack

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
    val converted: ItemStack = player.inventory.getCurrentItem
    val currentSlot: Int = player.inventory.currentItem

    convertTool(player, converted, currentSlot)
  }

  def convertTool(player: EntityPlayerMP, converted: ItemStack, currentSlot: Int) {
    val toolStack: ItemStack = EiItemTool.createEiTool(converted, player)
    player.inventory.decrStackSize(currentSlot, 1)
    player.inventory.setInventorySlotContents(currentSlot, toolStack)


  }

  def getCommandUsage(var1: ICommandSender): String = "ei.command.usage:ei "

  override def compareTo(par1Obj: ICommand): Int = {
    this.compareTo(par1Obj.asInstanceOf[ICommand])
  }
}
