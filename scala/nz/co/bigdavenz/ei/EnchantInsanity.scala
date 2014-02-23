package nz.co.bigdavenz.ei

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.event.FMLServerStartingEvent
import nz.co.bigdavenz.ei.processors.{EventProcessor, CommandProcessor}
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.item.EiItems
import net.minecraft.creativetab.CreativeTabs
import nz.co.bigdavenz.ei.lib.ModReference
import nz.co.bigdavenz.ei.creativeTabs.CreativeTabEi
import net.minecraftforge.common.MinecraftForge

/**
 * Enchant Insanity
 *
 * Created by BigDaveNz on 10/01/2014
 *
 * Main Mod Class
 */

@Mod(modid = "ei", name = "Enchant Insanity", version = "0.0.1", modLanguage = "scala")
object EnchantInsanity {
  val tabEi: CreativeTabs = new CreativeTabEi(ModReference.modName)

  @EventHandler def serverStarting(event: FMLServerStartingEvent) {
    event.registerServerCommand(CommandProcessor)
    Communicate.withConsoleDebug("Command Processor Registered")
  }

  @EventHandler def preInit(event: FMLPreInitializationEvent) {
    Communicate.withConsole("Enchant Insanity Loading...")
    MinecraftForge.EVENT_BUS.register(EventProcessor)
    EiItems.init()

  }

}