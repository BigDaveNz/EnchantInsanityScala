package nz.co.bigdavenz.ei.processors

import cpw.mods.fml.relauncher.{SideOnly, Side}
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.command.{CommandBase, ICommandSender, WrongUsageException}
import net.minecraft.entity.player.EntityPlayerMP
import nz.co.bigdavenz.ei.client.chat.Communicate

/**
 * Created by David J. Dudson on 6/01/14.
 */
object CommandProcessor extends CommandBase {

  @Override def getCommandName = {
    Reference.modId
  }

  @Override
  @SideOnly(Side.CLIENT)
  def processCommand(commandSender: ICommandSender, args: Array[Nothing]) {
    if (args.length > 0) {
      val commandName: String = args(0)
      System.arraycopy(args, 1, args, 0, args.length - 1)
      commandName.toLowerCase match {
        case "checkskill" =>
          processSkillCommand(commandSender, args)
        case "debugtoggle" =>
          Reference.debugMode = !Reference.debugMode
          (commandSender, "Debug mode toggled to: " + Reference.debugMode)
        case "debugcheck" =>
          Communicate.withCommandSender(commandSender, "Debug mode is: " + Reference.debugMode)
        case "version" =>
          Communicate.withCommandSender(commandSender, "Version: " + Reference.modVersion)
        case _ =>
          throw new WrongUsageException("Invalid Usage [skill|leaderboard|debugtoggle|debugcheck|version]", new Array[Nothing](0))
      }
    }
  }

  def processSkillCommand(commandSender: ICommandSender, args: Array[Nothing]) {
    if (args.length > 0) {
      val skillName: String = args(0)
      val player: EntityPlayerMP = commandSender.asInstanceOf[EntityPlayerMP]
      val xp = 0
      val level = 0
      Communicate.withCommandSender(commandSender, "Player: " + player.getDisplayName + " Skill: " + skillName + " XP: " + xp + " Level: " + level)
    }
    else throw new WrongUsageException("Invalid Usage [ei checkskill (skillname)]", new Array[Nothing](0))
  }
  def getCommandUsage(commandSender: ICommandSender): Boolean = true

  def compareTo(o: Object): Int = 0
}
