package nz.co.bigdavenz.ei.file

import net.minecraft.nbt.NBTTagCompound
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.lib.ModReference

/**
 * Created by David J. Dudson on 18/02/14.
 *
 * Handles world related Data
 */
object WorldData {

  private var worldData: NBTTagCompound = new NBTTagCompound
  private val ownershipData: NBTTagCompound = new NBTTagCompound


  def createWorldData {
    ownershipData.setTag("Ownership", worldData)
    worldData.setString("SaveVersion", ModReference.modVersion)

  }

  def getOwnershipData: NBTTagCompound = {
    worldData.getCompoundTag("Ownership")
  }

  def getWorldData: NBTTagCompound = {
    worldData
  }

  def setWorldData(data: NBTTagCompound): Unit = {
    worldData = data
    Communicate.withConsole("WorldData Loaded/Overriden")
  }
}
