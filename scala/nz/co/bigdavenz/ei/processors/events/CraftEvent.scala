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
    val oldField = XmlHelper.getPlayerPackage(event.player)

    Communicate.withConsoleDebug("Item Crafted and XP Given")
  }

  def onSmelt(event: PlayerEvent.ItemSmeltedEvent) {
    //    val oldField = PlayerData.getField(event.player.getDisplayName, "Craft", event.smelting.getDisplayName, NBTTypeEnum.INT)
    //    PlayerData.setField(event.player.getDisplayName, "Craft", event.smelting.getDisplayName, oldField + event.smelting.stackSize)
    Communicate.withConsoleDebug("Item Crafted and XP Given")
  }
}
