package nz.co.bigdavenz.ei

import cpw.mods.fml.common.{Mod,SidedProxy}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.event.FMLServerStartingEvent
import nz.co.bigdavenz.ei.lib.Reference
import nz.co.bigdavenz.ei.processors.CommandProcessor
import nz.co.bigdavenz.ei.client.chat.Communicate

/**
 * Enchant Insanity
 *
 * Created by BigDaveNz
 *
 * Main Mod Class
 */

@Mod(modid = Reference.modId, name = Reference.modName, version = "0.0.1", modLanguage = "scala")
object EnchantInsanity {

  //@SidedProxy(clientSide = Reference.clientProxy, serverSide = Reference.commonProxy)

  @EventHandler def serverStarting(event: FMLServerStartingEvent) {
    event.registerServerCommand(CommandProcessor)
  }

  @EventHandler def preInit(event: FMLPreInitializationEvent) {
    Communicate.withConsole("Enchant Insanity Loading...")
  }

}