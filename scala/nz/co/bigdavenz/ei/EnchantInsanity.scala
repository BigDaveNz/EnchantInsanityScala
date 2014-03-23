package nz.co.bigdavenz.ei

import scala.xml.XML

import cpw.mods.fml.common.{FMLCommonHandler, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPreInitializationEvent, FMLServerStartingEvent, FMLServerStoppingEvent}
import net.minecraft.creativetab.CreativeTabs
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.creativeTabs.CreativeTabEi
import nz.co.bigdavenz.ei.file.XmlHelper
import nz.co.bigdavenz.ei.item.EiItems
import nz.co.bigdavenz.ei.lib.ModReference
import nz.co.bigdavenz.ei.processors.{CommandProcessor, EventProcessor}
import scala.tools.nsc.Properties

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
    try
      //XmlHelper.mainPackage =  XML.loadFile("C:/Users/djd_000/Documents/EiData.ei")
    FMLCommonHandler.instance().bus().register(new EventProcessor)
    EiItems.init()
    Communicate.withConsole(Properties.versionNumberString)
  }

  @EventHandler def serverStopping(event: FMLServerStoppingEvent) {
    XML.save("C:/Users/djd_000/Documents/EiData.ei", XmlHelper.toXml("Main", XmlHelper.mainPackage))
  }

}