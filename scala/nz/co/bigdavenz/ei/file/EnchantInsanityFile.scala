package nz.co.bigdavenz.ei.file

import java.io.{IOException, DataOutputStream, FileOutputStream, File}
import net.minecraft.nbt.{CompressedStreamTools, NBTTagCompound}
import net.minecraft.world.storage.SaveHandler
import net.minecraft.server.MinecraftServer

/**
 * Created by David J. Dudson on 16/01/14.
 *
 * Responsible for the serialization of everything Enchant Insanity
 */
object EnchantInsanityFile extends SaveHandler {

  //todo get working
  var eiData: MainData = new MainData

  val saveHandler: SaveHandler = MinecraftServer.getServer.getActiveAnvilConverter.getSaveLoader(MinecraftServer.getServer.getFolderName, false).asInstanceOf[SaveHandler]

  var eiDirectory: File
  var saveDirectoryName: String

  def saveData(eiData: EiData) {

    val eiNBT: NBTTagCompound = new NBTTagCompound
    eiData.writeToNBT(eiNBT)
    if (this.saveHandler != null) {
      try {
        val file1: File = this.saveHandler.getMapFileFromName(eiData.mapName)
        if (file1 != null) {
          val fileOutputStream: FileOutputStream = new FileOutputStream(file1)
          CompressedStreamTools.writeCompressed(eiNBT, fileOutputStream)
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

  def EnchantInsanityFile(par1File: File, par2Str: String, par3: Boolean) {
    this.eiDirectory = new File(par1File, par2Str)
    this.eiDirectory.mkdirs
    this.saveDirectoryName = par2Str
    if (par3) {
      this.playersDirectory.mkdirs
    }
    setSessionLock
  }

  /**
   * Creates a session lock file for this process
   */
  def setSessionLock {
    try {
      val file1: File = new File(this.eiDirectory, "session.lock")
      val dataoutputstream: DataOutputStream = new DataOutputStream(new FileOutputStream(file1))
      try {
        dataoutputstream.writeLong(MinecraftServer.getSystemTimeMillis)
      }
      finally {
        dataoutputstream.close
      }
    }
    catch {
      case ioexception: IOException => {
        ioexception.printStackTrace
        throw new RuntimeException("Failed to check session lock, aborting")
      }
    }
  }
}
