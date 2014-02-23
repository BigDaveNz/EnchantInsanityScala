package nz.co.bigdavenz.ei.processors.events

import nz.co.bigdavenz.ei.core.chat.Communicate
import cpw.mods.fml.common.gameevent.PlayerEvent
import nz.co.bigdavenz.ei.file.PlayerData

/**
 * Created by David J. Dudson on 11/02/14.
 *
 * Deals with the crafting event
 */
object CraftEvent {

  def onCraft(event: PlayerEvent.ItemCraftedEvent) {
    PlayerData.setField(event.player.getDisplayName, "Craft", event.crafting.getDisplayName, PlayerData.getField(event.player.getDisplayName, "Craft", event.crafting.getDisplayName, Int.getClass.toString).asInstanceOf[Int] + event.crafting.stackSize)
    Communicate.withConsoleDebug("Item Crafted and XP Given")
  }

  def onSmelt(event: PlayerEvent.ItemSmeltedEvent) {
    PlayerData.setField(event.player.getDisplayName, "Craft", event.smelting.getDisplayName, PlayerData.getField(event.player.getDisplayName, "Craft", event.smelting.getDisplayName, Int.getClass.toString).asInstanceOf[Int] + event.smelting.stackSize)
    Communicate.withConsoleDebug("Item Crafted and XP Given")
  }
}
