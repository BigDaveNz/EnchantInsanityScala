package nz.co.bigdavenz.ei.processors.commands

import net.minecraft.command.ICommandSender
import nz.co.bigdavenz.ei.core.chat.Communicate

/**
 * Created by David J. Dudson on 18/02/14.
 *
 * All help commands go here
 */
object Help {

  def process(commandSender: ICommandSender, value: String): Unit = {
    Communicate.withCommandSender(commandSender, getHelpInfo(value))
  }

  def getHelpInfo(value: String): String = {
    value.toLowerCase match {
      case "debugtoggle" => "Advanced User Command- Toggles whether extra debug information is added to the Console"
      case "debugcheck" => "Advanced User Command - Tells you whether debug mode is on or off"
      case "version" => "Basic Command - Returns the current version of Enchant Insanity"
      case "convert" => "Basic Command - Converts current held item into it's Enchant Insanity counterpart, currently converts ItemTool/ItemShears/ItemSword. Note: Any mod added 'abilities' will be lost"
      case "commands" => "Basic Command - Current commands are: debugtoggle|debugcheck|version|convert|xp[object] - for help with any of them type /help [command]"
      case "xp" => "Advanced User Command - Returns a list of xp values for alot of things, Item and Blocks, also things like running and swimming are included"
      case "help" => "Basic Command - You are using it right now!! Congratulations, you already know what it does. list all help topics via: (/help topics)"
      case "brag" => "Advanced User Command - Allows important people to shamelessly brag/promote themselves with a server chat broadcast"
      case "bigdavenz" => "BigDaveNz is the I the author of Enchant Insanity, I hope you have as much fun playing with the mod as I did creating it!!!"
      case "enchantinsanity" => "Enchant Insanity is the mod which adds a whole new skill based enchanting/ability system to the game, and much more"
      case "topics" => "[DebugToggle|DebugCheck|Version|Convert|Commands|XP|Help|Brag|BigDaveNz|EnchantInsanity|Topics]"
      case _ => "Invalid help topic try: (/help topics) to find a list of help topics. Note: All help topics are one word and are NOT case dependant"
    }
  }
}
