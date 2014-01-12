package nz.co.bigdavenz.ei.client.chat

import net.minecraft.util.{ChatComponentText, IChatComponent}
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayer
import cpw.mods.fml.common.FMLLog

/**
 * Created by David J. Dudson on 10/01/14.
 */
object Communicate {

  var EIChatComponent: IChatComponent = createEIChatComponent(Reference.modId.toUpperCase);

  def withCommandSender(player: ICommandSender, message: String) {
    withPlayer(player.asInstanceOf[EntityPlayer], message)
  }

  private def createEIChatComponent(string: String): IChatComponent = {
    new ChatComponentText(string)
  }

  private def createChatComponent(message: String): IChatComponent = {
    EIChatComponent.func_150257_a(new ChatComponentText(message))
  }

  def withPlayer(player: EntityPlayer, message: String) {
    player.func_146105_b(createChatComponent(message))
  }

  def withAllPlayers(message: String) {
    Reference.configManager.func_148539_a(createChatComponent(message))
  }

  def withClient(message: String) {
    withPlayer(Reference.mc.thePlayer, message)
  }

  def withPlayerDebug(player: EntityPlayer, message: String, debugType: String = "General") {
    if (Reference.debugMode) {
      withPlayer(player, "[Debug:" + debugType + "] " + message)
    }
  }

  def withConsoleDebug(message: String, debugType: String = "General") {
    if (Reference.debugMode) {
      FMLLog.info("[EI DEBUG] " + "[" + debugType + "] " + message)
    }
  }

  def withConsole(message: String) {
    if (Reference.debugMode) {
      FMLLog.info("[EI] " + message)
    }
  }

}
