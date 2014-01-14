package nz.co.bigdavenz.ei.core.traits

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.server.MinecraftServer

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Allows Items/Blocks to be ownable
 */
trait Ownable {

  private var owner: String = ""

  var getOwnerName = owner

  def setOwner(player: EntityPlayer) {
    owner = player.getDisplayName
  }

  def getOwner: EntityPlayer = {
    MinecraftServer.getServer.getConfigurationManager.getPlayerForUsername(owner)
  }
}
