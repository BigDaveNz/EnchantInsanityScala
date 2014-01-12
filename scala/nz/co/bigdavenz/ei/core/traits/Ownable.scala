package nz.co.bigdavenz.ei.core.traits

import net.minecraft.entity.player.EntityPlayer

/**
 * Created by David J. Dudson on 11/01/14.
 */
trait Ownable {

  private var owner: EntityPlayer = null

  var getOwner = owner

  def setOwner(player: EntityPlayer) {
    owner = player
  }
}
