package nz.co.bigdavenz.ei.processors

import cpw.mods.fml.common.gameevent.PlayerEvent
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.processors.events.CraftEvent
import nz.co.bigdavenz.ei.item.EiItem
import net.minecraft.item.ItemStack
import net.minecraftforge.event.world.WorldEvent
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import nz.co.bigdavenz.ei.file.{DataPackage, XmlHelper}

//import nz.co.bigdavenz.ei.file.EnchantInsanityFile


/**
 * Created by David J. Dudson on 13/01/14.
 *
 * Processes all tapped into Events
 */
class EventProcessor {

  @SubscribeEvent
  def onPlayerLogin(event: PlayerEvent.PlayerLoggedInEvent) {
    if (XmlHelper.getPlayersPackage.containsKey(event.player.getDisplayName)) {
      val tutPack: DataPackage = XmlHelper.getPlayerPackage(event.player, "Tutorials")
      if (tutPack.getContents("FirstLogin") == false) {
        Communicate.withPlayer(event.player, "Welcome to Enchant Insanity Testing!")
        tutPack.setContents("FirstLogin", true)
      }
    } else {
      XmlHelper.addNewPlayerToMainXml(event.player)
      Communicate.withPlayer(event.player, "Ei Save created for you!")
    }
  }

  @SubscribeEvent
  def onPlayerPickup(event: PlayerEvent.ItemPickupEvent) {
    val itemStack: ItemStack = event.pickedUp.getEntityItem
    itemStack.getItem match {
      case _: EiItem if itemStack.stackTagCompound.hasKey("Owner") =>
        val owner: String = itemStack.stackTagCompound.getString("Owner")
        event.player.getDisplayName match {
          case `owner` => Communicate.withPlayer(event.player, "Well at least you retrieved it.")
          case _ => Communicate.withPlayer(event.player, "This " + itemStack.getDisplayName + " Does not belong to you!! You can't pick it up!")
            event.setCanceled(true)
        }
      case _ =>
        Communicate.withConsoleWarning("EI ITEM", "Item attempted to be created when it had no owner attached. This usually means the Item was improperly created")
    }
  }

  @SubscribeEvent
  def onPlayerCraft(event: PlayerEvent.ItemCraftedEvent) {
    CraftEvent.onCraft(event)
    Communicate.withConsole("Player: " + event.player + ". Smelted Item: " + event.crafting.getDisplayName + ". Amount: " + event.crafting.stackSize)
  }

  @SubscribeEvent
  def onPlayerSmelt(event: PlayerEvent.ItemSmeltedEvent) {
    CraftEvent.onSmelt(event)
    Communicate.withConsole("Player: " + event.player + ". Smelted Item: " + event.smelting.getDisplayName + ". Amount: " + event.smelting.stackSize)
  }

  @SubscribeEvent
  def onWorldSave(event: WorldEvent.Save) {
    //EnchantInsanityFile.saveData(EnchantInsanityFile.eiData)
    Communicate.withConsoleDebug("Enchant Insanity Data Saved", "Save")
  }

  @SubscribeEvent
  def onWorldLoad(event: WorldEvent.Load) {
    //EnchantInsanityFile.loadData()
  }
}
