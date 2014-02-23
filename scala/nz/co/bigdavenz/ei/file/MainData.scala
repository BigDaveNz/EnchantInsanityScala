package nz.co.bigdavenz.ei.file

import net.minecraft.nbt.NBTTagCompound
import nz.co.bigdavenz.ei.lib.ModReference

/**
 * Created by David J. Dudson on 10/02/14.
 *
 * All data is in this file
 */
class MainData extends EiData {

  var mapName: String = "EI Data"

  def writeToNBT(nbtCompound: NBTTagCompound): Unit = {

    nbtCompound.setString("Save Version", ModReference.modVersion)
    nbtCompound.setTag("Player Data", PlayerData.getPlayerData)
    nbtCompound.setTag("World Data", WorldData.getWorldData)
  }

  def loadFromNBT(nbtCompound: NBTTagCompound): Unit = {

    WorldData.setWorldData(nbtCompound.getCompoundTag("World Data"))
    PlayerData.setPlayerData(nbtCompound.getCompoundTag("Player Data"))
  }
}
