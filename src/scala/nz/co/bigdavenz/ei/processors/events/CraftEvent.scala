package nz.co.bigdavenz.ei.processors.events

import nz.co.bigdavenz.ei.core.chat.Communicate
import cpw.mods.fml.common.gameevent.PlayerEvent
import nz.co.bigdavenz.ei.file.XmlHelper

/**
 * Created by David J. Dudson on 11/02/14.
 *
 * Deals with the crafting event
 */
object CraftEvent {

  def onCraft(event: PlayerEvent.ItemCraftedEvent) {
    val oldField: Int = XmlHelper.getPlayerPackage(event.player, "Craft").getHeadOfType[Int](event.crafting.getDisplayName)
    XmlHelper.getPlayerPackage(event.player, "Craft").addValueToArrayOfType[Int](event.crafting.getDisplayName, oldField + event.crafting.stackSize)

    Communicate.withConsoleDebug("Item Crafted: " + event.crafting.getDisplayName + " NewXpAmount: " + oldField + event.crafting.stackSize)
  }

  def onSmelt(event: PlayerEvent.ItemSmeltedEvent) {
    val oldField: Int = XmlHelper.getPlayerPackage(event.player, "Craft").getHeadOfType[Int](event.smelting.getDisplayName)
    XmlHelper.getPlayerPackage(event.player, "Craft").addValueToArrayOfType[Int](event.smelting.getDisplayName, oldField + event.smelting.stackSize)

    Communicate.withConsoleDebug("Item Smelted: " + event.smelting.getDisplayName + " NewXpAmount: " + oldField + event.smelting.stackSize)
  }
}
