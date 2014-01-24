package nz.co.bigdavenz.ei.processors

import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.gameevent.PlayerEvent
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.processors.events.LoginEvent
import nz.co.bigdavenz.ei.item.EiItem
import net.minecraft.item.{ItemStack, Item}


/**
 * Created by David J. Dudson on 13/01/14.
 *
 * Processes all tapped into Events
 */
object EventProcessor {

  @EventHandler
  def onPlayerLogin(event: PlayerEvent.PlayerLoggedInEvent) {
    LoginEvent.process(event)
  }

  @EventHandler
  def onPlayerPickup(event: PlayerEvent.ItemPickupEvent) {
    val itemStack: ItemStack = event.pickedUp.getEntityItem
    itemStack.getItem match {
      case _: EiItem if itemStack.stackTagCompound.hasKey("Owner") =>
        val owner: String = itemStack.stackTagCompound.getString("Owner")
        event.player.getDisplayName match {
          case `owner` =>
          case _ => Communicate.withPlayer(event.player, "This " + itemStack.getDisplayName + " Does not belong to you!! You can't pick it up!")
            event.setCanceled(true)
        }
      case _=>
    }
  }
}
