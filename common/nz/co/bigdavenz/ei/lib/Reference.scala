package nz.co.bigdavenz.ei.lib

import net.minecraft.client.Minecraft
import net.minecraft.server.MinecraftServer
import net.minecraft.server.management.ServerConfigurationManager
import net.minecraft.client.renderer.texture.TextureManager
import cpw.mods.fml.common.FMLCommonHandler

/**
 * Created by David J. Dudson on 5/01/14.
 */
object Reference {

  val modId: String = "ei"
  val modName: String = "Enchant Insanity"
  val modVersion: String = "0.0.1"

  var debugMode = true

  val mc:Minecraft =  Minecraft.getMinecraft
  val configManager = MinecraftServer.getServer.getConfigurationManager
  val textureManager = mc.getTextureManager
  val eventBus = FMLCommonHandler.instance().bus()

  val clientProxy = "nz.co.bigdavenz.core.proxy.ClientProxy"
  val commonProxy = "nz.co.bigdavenz.core.proxy.CommonProxy"
}
