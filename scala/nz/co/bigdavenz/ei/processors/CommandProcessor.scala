package nz.co.bigdavenz.ei.processors

import cpw.mods.fml.relauncher.{SideOnly, Side}
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.command.{ICommand, CommandBase, ICommandSender, WrongUsageException}
import net.minecraft.entity.player.EntityPlayerMP
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.item.EiItems
import net.minecraft.item.ItemStack

/**
 * Created by David J. Dudson on 6/01/14.
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
        case "eipick" =>
          commandSender.asInstanceOf[EntityPlayerMP].inventory.addItemStackToInventory(new ItemStack(EiItems.enchantedPickaxe))
        case _ =>
          throw new WrongUsageException("Invalid Usage [skill|leaderboard|debugtoggle|debugcheck|version]", new Array[Nothing](0))
      }
    }
  }

  def getCommandUsage(var1: ICommandSender): String = "ei.command.usage:ei "

  override def compareTo(par1Obj: ICommand): Int = {
    this.compareTo(par1Obj.asInstanceOf[ICommand])
  }
}
