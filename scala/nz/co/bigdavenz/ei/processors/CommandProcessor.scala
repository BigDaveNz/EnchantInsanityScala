package nz.co.bigdavenz.ei.processors

import cpw.mods.fml.relauncher.{SideOnly, Side}
import nz.co.bigdavenz.ei.lib.{GeneralReference, ModReference}
import net.minecraft.command.{CommandBase, ICommandSender, WrongUsageException}
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.processors.commands.{Help, Experience, ConvertObject}
import nz.co.bigdavenz.ei.file.PlayerData
import nz.co.bigdavenz.ei.core.utils.Utils

/**
 * Created by David J. Dudson on 6/01/14.
 *
 * processes all commands
 */

object CommandProcessor extends CommandBase {

  def getCommandName = {
    ModReference.modId
  }

  override def canCommandSenderUseCommand(commandSender: ICommandSender): Boolean = {
    true
  }

  @SideOnly(Side.CLIENT)
  def processCommand(commandSender: ICommandSender, args: Array[String]) {
    args.length match {
      case 0 =>
        throw new WrongUsageException("No command given, usage: ei [command]", new Array[Nothing](0))
      case 1 => {
        val commandName: String = args(0)
        commandName.toLowerCase match {
          case "debugtoggle" => // Toggles whether debug mode is active //
            ModReference.debugMode = !ModReference.debugMode
            Communicate.withCommandSender(commandSender, "Debug mode toggled to: " + ModReference.debugMode)
          case "debugcheck" => // Checks whether debugmode is active //
            Communicate.withCommandSender(commandSender, "Debug mode is: " + ModReference.debugMode)
          case "version" => // Returns current version number //
            Communicate.withCommandSender(commandSender, "Version: " + ModReference.modVersion)
          case "convert" => // Converts items into an EI Item //
            ConvertObject.convert(commandSender)
          case "help" => // Wrong usage for help help //
            throw new WrongUsageException("/ei help [topic]", new Array[Nothing](0))
          case "xp" => // Wrong usage for xp command //
            throw new WrongUsageException("/ei xp [value] or /ei xp [player] [value]", new Array[Nothing](0))
          case "brag" => // If the //
            PlayerData.getField(commandSender.getCommandSenderName, "Misc", "Status", GeneralReference.stringClassToString).toString match {
              case "Important" =>
                Communicate.withAllPlayers("To find out more about why " + commandSender.getCommandSenderName + " is a VIP go to: " + " #ShamelessPromotions")
              case _ =>
                Communicate.withCommandSender(commandSender, "You obviously aren't important enough to get personal brag rights, If you think this is in error, contact BigDaveNz or submit a pull request on GitHub for your own brag statement")
            }
          case _ =>
            Communicate.withCommandSender(commandSender, "Invalid command option.")
        }
      }
      case 2 =>
        val commandName: String = args(0)
        val value: String = args(1)

        commandName.toLowerCase match {
          case "xp" =>
            Experience.printExperienceTable(commandSender, commandSender.getCommandSenderName, value)
          case "help" =>
            Help.process(commandSender, value)
        }

      case 3 =>
        val commandName: String = args(0)
        val arg1: String = args(1)
        val arg2: String = args(2)
        commandName.toLowerCase match {
          case "xp" =>
            Experience.printExperienceTable(commandSender, arg1, arg2)
            Communicate.withPlayer(Utils.getPlayerForUserName(arg1), commandSender.getCommandSenderName + " just looked up your Xp for: " + arg2)

          case _ =>
            throw new WrongUsageException("Invalid 3 argument command")
        }
      case _ =>
    }
  }

  def getCommandUsage(var1: ICommandSender): String = "ei.command.usage:ei "
}
