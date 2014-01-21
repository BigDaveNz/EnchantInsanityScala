package nz.co.bigdavenz.ei.nbt

import net.minecraftforge.common.IExtendedEntityProperties
import net.minecraft.entity.Entity
import net.minecraft.world.World
import net.minecraft.nbt.NBTTagCompound
import nz.co.bigdavenz.ei.core.chat.Communicate
import net.minecraft.entity.player.EntityPlayer

/**
 * Created by David J. Dudson on 16/01/14.
 *
 * class for saving player data
 */
class playerNBT extends IExtendedEntityProperties {
  def saveNBTData(compound: NBTTagCompound): Unit = ???

  def loadNBTData(compound: NBTTagCompound): Unit = ???

  def init(entity: Entity, world: World){
    Communicate.withConsoleDebug("NBT created for: " + entity.asInstanceOf[EntityPlayer].getDisplayName, "NBT" )
  }
}
