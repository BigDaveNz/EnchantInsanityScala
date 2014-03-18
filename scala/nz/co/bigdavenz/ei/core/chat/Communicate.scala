package nz.co.bigdavenz.ei.core.chat

import net.minecraft.util.{ChatComponentText, IChatComponent}
import nz.co.bigdavenz.ei.lib.ModReference
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayer
import cpw.mods.fml.common.FMLLog
import net.minecraft.client.Minecraft
import net.minecraft.server.MinecraftServer
import cpw.mods.fml.relauncher.{Side, SideOnly}

/**
 * Created by David J. Dudson on 10/01/14.
 *
 * Used for communicating with client server and player
 */
object Communicate {

  def withCommandSender(player: ICommandSender, message: String) {
    withPlayer(player.asInstanceOf[EntityPlayer], message)
  }

  private def createEIChatComponent(string: String): IChatComponent = {
    new ChatComponentText("[" + string + "] ")
  }

  private def createChatComponent(message: String): IChatComponent = {
    createEIChatComponent(ModReference.modId.toUpperCase).appendSibling(new ChatComponentText(message))
  }

  def withPlayer(player: EntityPlayer, message: String) {
    try {
      player.addChatComponentMessage(createChatComponent(message))
      Communicate.withConsole("Message to: " + player.getDisplayName + " Message: " + message)
    }
    catch {
      case _: NullPointerException =>
        Communicate.withConsoleWarning("Chat", "Attempted to communicate with a player that is not online! Player: " + player.getDisplayName)
    }
  }

  def withPlayerWarning(player: EntityPlayer, message: String) {
    player.addChatComponentMessage(createChatComponent("[WARNING]" + message))
  }

  def withAllPlayers(message: String) {
    MinecraftServer.getServer.getConfigurationManager.sendChatMsg(createChatComponent("[BROADCAST]" + message))
  }

  def withAllPlayersWarning(message: String) {
    MinecraftServer.getServer.getConfigurationManager.sendChatMsg(createChatComponent("[BROADCAST] [WARNING] " + message))
  }

  @SideOnly(Side.CLIENT)
  def withClient(message: String) {
    withPlayer(Minecraft.getMinecraft.thePlayer, message)
  }

  def withPlayerDebug(player: EntityPlayer, message: String, debugType: String = "General") {
    if (ModReference.debugMode) {
      withPlayer(player, "[Debug:" + debugType + "] " + message)
    }
  }

  def withConsoleDebug(message: String, debugType: String = "General") {
    if (ModReference.debugMode) {
      FMLLog.info("[EI DEBUG] " + "[" + debugType + "] " + message)
    }
  }

  def withConsoleWarning(warningType: String, message: String) {
    FMLLog.warning("[EI] " + "[" + warningType.toUpperCase + "] " + message)
  }

  def withConsoleSevere(severeType: String, message: String) {
    FMLLog.severe("[EI] " + "[" + severeType.toUpperCase + "] " + message)
  }

  def withConsole(message: String) {
    FMLLog.info("[EI] " + message)
  }

}
