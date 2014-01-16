package nz.co.bigdavenz.ei.core.chat

import net.minecraft.util.{ChatComponentText, IChatComponent}
import nz.co.bigdavenz.ei.lib.Reference
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
    createEIChatComponent(Reference.modId.toUpperCase).func_150257_a(new ChatComponentText(message))
  }

  def withPlayer(player: EntityPlayer, message: String) {
    player.func_146105_b(createChatComponent(message))
  }

  def withAllPlayers(message: String) {
    MinecraftServer.getServer.getConfigurationManager.func_148539_a(createChatComponent(message))
  }

  @SideOnly(Side.CLIENT)
  def withClient(message: String) {
    withPlayer(Minecraft.getMinecraft.thePlayer, message)
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
    FMLLog.info("[EI] " + message)
  }

}
