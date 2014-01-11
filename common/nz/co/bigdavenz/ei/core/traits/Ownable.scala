package nz.co.bigdavenz.ei.core.traits

import net.minecraft.entity.player.EntityPlayer

/**
 * Created by David J. Dudson on 11/01/14.
 */
trait Ownable {

  private var player: EntityPlayer

  var getOwner = player

  def setOwner(owner: EntityPlayer) {
    player = owner
  }
}
