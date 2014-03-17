package nz.co.bigdavenz.ei.processors.events

import nz.co.bigdavenz.ei.core.chat.Communicate
import cpw.mods.fml.common.gameevent.PlayerEvent

/**
 * Created by David J. Dudson on 11/02/14.
 *
 * Deals with the crafting event
 */
object CraftEvent {

  def onCraft(event: PlayerEvent.ItemCraftedEvent) {
    //    val oldField = PlayerData.getField(event.player.getDisplayName, "Craft", event.crafting.getDisplayName, NBTTypeEnum.INT)
    //    Communicate.withConsoleDebug(oldField.toString)

    //ayerData.setField(event.player.getDisplayName, "Craft", event.crafting.getDisplayName,  oldField + event.crafting.stackSize)
    Communicate.withConsoleDebug("Item Crafted and XP Given")
  }

  def onSmelt(event: PlayerEvent.ItemSmeltedEvent) {
    //    val oldField = PlayerData.getField(event.player.getDisplayName, "Craft", event.smelting.getDisplayName, NBTTypeEnum.INT)
    //    PlayerData.setField(event.player.getDisplayName, "Craft", event.smelting.getDisplayName, oldField + event.smelting.stackSize)
    Communicate.withConsoleDebug("Item Crafted and XP Given")
  }
}
