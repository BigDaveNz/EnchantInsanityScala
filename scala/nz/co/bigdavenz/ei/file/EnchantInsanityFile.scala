package nz.co.bigdavenz.ei.file

import java.io.{FileOutputStream, File}
import net.minecraft.nbt.{CompressedStreamTools, NBTTagCompound}
import net.minecraft.world.storage.SaveHandler
import net.minecraft.server.MinecraftServer

/**
 * Created by David J. Dudson on 16/01/14.
 *
 * Responsible for the serialization of everything Enchant Insanity
 */
object EnchantInsanityFile {
  val saveHandler: SaveHandler = MinecraftServer.getServer.getActiveAnvilConverter.getSaveLoader(MinecraftServer.getServer.getFolderName, false).asInstanceOf[SaveHandler]

  var Directory:File = new File(saveHandler.getWorldDirectory, "players")

  def saveData(eiData: EiData) {
    if (this.saveHandler != null) {
      try {
        val file1: File = this.saveHandler.getMapFileFromName(eiData.mapName)
        if (file1 != null) {
          val baseCompound: NBTTagCompound = new NBTTagCompound
          eiData.writeToNBT(baseCompound)
          val worldData: NBTTagCompound = new NBTTagCompound
          worldData.setTag("World Data", baseCompound)
          val fileOutputStream: FileOutputStream = new FileOutputStream(file1)
          CompressedStreamTools.writeCompressed(worldData, fileOutputStream)
          fileOutputStream.close
        }
      }
      catch {
        case e: Exception => {
          e.printStackTrace
        }
      }
    }
  }
}
