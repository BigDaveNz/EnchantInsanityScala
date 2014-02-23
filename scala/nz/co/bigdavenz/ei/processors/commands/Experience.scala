package nz.co.bigdavenz.ei.processors.commands

import nz.co.bigdavenz.ei.core.chat.Communicate
import net.minecraft.command.ICommandSender
import nz.co.bigdavenz.ei.file.PlayerData

/**
 * Created by David J. Dudson on 11/02/14.
 *
 * Handles the Experience Commands
 */

object Experience {

  def printExperienceTable(commandSender: ICommandSender, playerName: String, fieldName: String) {
    Communicate.withCommandSender(commandSender, "This would return the experience list for: " + fieldName)
    Communicate.withCommandSender(commandSender, fieldName + " information:")
    PlayerData.compoundNameList.foreach {
      compoundName: String =>
        val result = PlayerData.getField(playerName, compoundName, fieldName).toString
        result match {
          case "()" =>
          case _ =>
            Communicate.withCommandSender(commandSender, fieldName + " - " + compoundName + " XP: " + result)
        }
    }
  }
}
