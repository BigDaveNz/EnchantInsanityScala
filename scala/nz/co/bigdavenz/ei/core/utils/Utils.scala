package nz.co.bigdavenz.ei.core.utils

import net.minecraft.server.MinecraftServer
import net.minecraft.entity.player.EntityPlayer

/**
 * Created by David J. Dudson on 16/02/14.
 *
 * General utility functions
 */
object Utils {

  def getPlayerForUserName(name: String): EntityPlayer = {
    try {
      MinecraftServer.getServer.getConfigurationManager.getPlayerForUsername(name)
    }
    catch {
      case _: Exception =>
        null
    }

  }
}
