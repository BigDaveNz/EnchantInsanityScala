package nz.co.bigdavenz.ei.file

import net.minecraft.nbt.NBTTagCompound

/**
 * Created by David J. Dudson on 29/01/14.
 *
 * Handles all save data
 */
abstract class EiData {

  var mapName:String
  def writeToNBT(nbtCompuound:NBTTagCompound)

  def loadFromNBT(nbtCompound:NBTTagCompound)
}
